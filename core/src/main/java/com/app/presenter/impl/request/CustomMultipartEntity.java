package com.app.presenter.impl.request;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.Serializable;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;

import android.os.SystemClock;


/**
 * 可以监控上传进度的实体
 * 
 * @author xinjun
 * 
 */
public class CustomMultipartEntity extends MultipartEntity {

	private final ProgressListener listener;

	public CustomMultipartEntity(final ProgressListener listener) {

		super();

		this.listener = listener;

	}

	public CustomMultipartEntity(final HttpMultipartMode mode,
			final ProgressListener listener) {

		super(mode);

		this.listener = listener;

	}

	public CustomMultipartEntity(HttpMultipartMode mode, final String boundary,

	final Charset charset, final ProgressListener listener) {

		super(mode, boundary, charset);

		this.listener = listener;

	}
	
	long pos;
	@Override
	public void addPart(String name, ContentBody contentBody) {
		super.addPart(name, contentBody);
		if(contentBody instanceof FileBody){
			FileBody fb=(FileBody) contentBody;
			FileInfo info=new FileInfo();
			info.size=fb.getContentLength();
			info.startPos=pos;
			info.endPos=pos+info.size-1;
			info.filePath=fb.getFile().getPath();
			mFiles.put(name,info);
		}
		pos+=contentBody.getContentLength();
	}

	@Override
	public void writeTo(final OutputStream outstream) throws IOException {
		mStream=new CountingOutputStream(outstream, this.listener);
		super.writeTo(mStream);

	}

	public static interface ProgressListener {

		
		/**
		 * 
		 * @param paramKeyName 对应的属性名称,去除了file:
		 * @param currCompletedByteCounts 当前文件完成字节数
		 * @param currFileByteCounts 当前文件总字节数
		 * @param allFileCompletedByteCounts 所有文件完成字节数
		 * @param allFileByteCounts 所有文件总字节数
		 */
		void transferred(ProgressInfo info);

	}
	
	
	private CountingOutputStream mStream;
	private Map<String,FileInfo> mFiles=new LinkedHashMap<String,FileInfo>();
	
	
	private static class FileInfo{
		long startPos;
		long endPos;
		long size;
		long currPos;
		String filePath;
	}
	
	
	public long getAllFileSize(Map<String,FileInfo> mFiles){
		long size=0;
		Iterator<Entry<String, FileInfo>> iterator = mFiles.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, FileInfo> entry=iterator.next();
			FileInfo value = entry.getValue();
			size+=value.size;
		}
		return size;
	}
	
	public ProgressInfo calcProgressInfo(long transferred,Map<String,FileInfo> mFiles){
		
		ProgressInfo result=new ProgressInfo();
		
		Iterator<Entry<String, FileInfo>> iterator = mFiles.entrySet().iterator();
		int index=0;
		long allCompletedSize=0;
		while(iterator.hasNext()){
			index++;
			Entry<String, FileInfo> entry=iterator.next();
			String key = entry.getKey();
			FileInfo value = entry.getValue();
			
			if(value.startPos<=transferred && value.endPos>=transferred){
				result.keyName=key;
				result.filePath=value.filePath;
				
				result.index=index;
				result.allFileCount=mFiles.size();
				
				value.currPos=transferred-value.startPos;
				result.currCompletedSize=value.currPos;
				result.currSize=value.size;
				result.allCompletedSize=allCompletedSize+(transferred-value.startPos);
				result.allSize=getAllFileSize(mFiles);
				
				result.currProgress=result.currCompletedSize*1.0/result.currSize;
				result.allProgress=result.allCompletedSize*1.0/result.allSize;
				
				return result;
			}
			allCompletedSize+=value.size;
		}
		return null;
	}
	
	/**
	 * 进度信息
	 * @author XINJUN
	 *
	 */
	@SuppressWarnings("serial")
	public static class ProgressInfo implements Serializable{
		private String keyName;
		private String filePath;
		private int index;
		private int allFileCount;
		private long currCompletedSize;
		private long currSize;
		private long allCompletedSize;
		private long allSize;
		
		private double currProgress;
		private double allProgress;
		
		
		private double speed;
		
		public String getKeyName() {
			return keyName;
		}
		public String getFilePath() {
			return filePath;
		}
		public long getCurrCompletedSize() {
			return currCompletedSize;
		}
		public long getCurrSize() {
			return currSize;
		}
		public long getAllCompletedSize() {
			return allCompletedSize;
		}
		public long getAllSize() {
			return allSize;
		}
		public int getIndex() {
			return index;
		}
		public int getAllFileCount() {
			return allFileCount;
		}
		public double getSpeed() {
			return speed;
		}
		public double getCurrProgress() {
			return currProgress;
		}
		public double getAllProgress() {
			return allProgress;
		}
		@Override
		public String toString() {
			return "ProgressInfo [keyName=" + keyName + ", filePath="
					+ filePath + ", index=" + index + ", allFileCount="
					+ allFileCount + ", currCompletedSize=" + currCompletedSize
					+ "bytes, currSize=" + currSize + "bytes, allCompletedSize="
					+ allCompletedSize + "bytes, allSize=" + allSize + "bytes, speed="
					+ speed + "Kb/s]";
		}
	}

	/**
	 * 
	 * @author XINJUN
	 *
	 */
	public class CountingOutputStream extends FilterOutputStream {

		private final ProgressListener listener;
		private long transferred;
		private long startTime;
		private double speed;
		
		public CountingOutputStream(final OutputStream out,
				final ProgressListener listener) {

			super(out);
			this.listener = listener;
			this.transferred = 0;
			this.startTime=System.currentTimeMillis();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(transferred<pos){
						speed=transferred*1.0/(System.currentTimeMillis()-startTime)*1000/1024;
//						MyLog.outLogDetail(transferred+"---"+pos+"---"+speed+"kb/s");
						SystemClock.sleep(5);
					}
				}
			}).start();
			
		}

		public void write(byte[] b, int off, int len) throws IOException {
			out.write(b, off, len);
			this.transferred += len;
			publish();

		}

		/**
		 * 发布进度
		 */
		private void publish() {
			if (this.listener != null){
				ProgressInfo progressInfo = calcProgressInfo(transferred, mFiles);
				if(progressInfo!=null){
					progressInfo.speed=speed;
					this.listener.transferred(progressInfo);
				}
			}
		}

		public void write(int b) throws IOException {
			out.write(b);
			this.transferred++;
			publish();
		}

	}

}
