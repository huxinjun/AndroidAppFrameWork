package com.easyjson.model;

import java.io.Serializable;
import com.easyjson.annotation.JSONClass;
import com.easyjson.annotation.JSONField;
import java.util.ArrayList;

/**
 *
 * @author EasyJson By xinjun
 *
 */
public class Json implements Serializable{

	/**
	 * 自动生成的序列化串号
	 */
	private static final long serialVersionUID = 4708508814116116179L;
	/**
	 * 
	 */
	private int code;
	/**
	 * 
	 */
	@JSONField("resultCode")
	private ArrayList<ResultCode> resultCode;
	/**
	 * 
	 */
	private String message;


	//**********************************************Getter and Setter************************************************

	public int getCode(){
		return this.code;
	}
	public void setCode(int code){
		this.code=code;
	}
	public ArrayList<ResultCode> getResultCode(){
		return this.resultCode;
	}
	public void setResultCode(ArrayList<ResultCode> resultCode){
		this.resultCode=resultCode;
	}
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message=message;
	}


	//**************************************************toString******************************************************

	@Override
	public String toString() {
		return "Json [code=" + code + ", resultCode=" + resultCode
				+ ", message=" + message + "]";
	}


	//**************************************************equals******************************************************

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Json other = (Json) obj;
		if (code != other.code)
			return false;
		if (resultCode == null) {
			if (other.resultCode != null && other.resultCode.size()!=0)
				return false;
		} else {
			for(int i=0;i<resultCode.size();i++)
				if (!resultCode.get(i).equals(other.resultCode.get(i)))
					return false;
		}
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	/**
	 *
	 * @author EasyJson By xinjun
	 *
	 */
	@JSONClass("resultCode")
	public static class ResultCode implements Serializable{

		/**
		 * 自动生成的序列化串号
		 */
		private static final long serialVersionUID = 4708508814116116179L;
		/**
		 * 
		 */
		private int uid;
		/**
		 * 
		 */
		private String gender;
		/**
		 * 
		 */
		private String nickname;
		/**
		 * 
		 */
		private int ages;
		/**
		 * 
		 */
		@JSONField("is_open_shops")
		private int isOpenShops;
		/**
		 * 
		 */
		private String avatar;
		/**
		 * 
		 */
		private int id;
		/**
		 * 
		 */
		@JSONField("is_open_skills")
		private int isOpenSkills;


		//**********************************************Getter and Setter************************************************

		public int getUid(){
			return this.uid;
		}
		public void setUid(int uid){
			this.uid=uid;
		}
		public int getIsOpenShops(){
			return this.isOpenShops;
		}
		public void setIsOpenShops(int isOpenShops){
			this.isOpenShops=isOpenShops;
		}
		public String getGender(){
			return this.gender;
		}
		public void setGender(String gender){
			this.gender=gender;
		}
		public String getNickname(){
			return this.nickname;
		}
		public void setNickname(String nickname){
			this.nickname=nickname;
		}
		public int getAges(){
			return this.ages;
		}
		public void setAges(int ages){
			this.ages=ages;
		}
		public int getIsOpenSkills(){
			return this.isOpenSkills;
		}
		public void setIsOpenSkills(int isOpenSkills){
			this.isOpenSkills=isOpenSkills;
		}
		public String getAvatar(){
			return this.avatar;
		}
		public void setAvatar(String avatar){
			this.avatar=avatar;
		}
		public int getId(){
			return this.id;
		}
		public void setId(int id){
			this.id=id;
		}


		//**************************************************toString******************************************************

		@Override
		public String toString() {
			return "ResultCode [uid=" + uid + ", isOpenShops=" + isOpenShops
					+ ", gender=" + gender + ", nickname=" + nickname
					+ ", ages=" + ages + ", isOpenSkills=" + isOpenSkills
					+ ", avatar=" + avatar + ", id=" + id
					+ "]";
		}


		//**************************************************equals******************************************************

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ResultCode other = (ResultCode) obj;
			if (uid != other.uid)
				return false;
			if (isOpenShops != other.isOpenShops)
				return false;
			if (gender == null) {
				if (other.gender != null)
					return false;
			} else if (!gender.equals(other.gender))
				return false;
			if (nickname == null) {
				if (other.nickname != null)
					return false;
			} else if (!nickname.equals(other.nickname))
				return false;
			if (ages != other.ages)
				return false;
			if (isOpenSkills != other.isOpenSkills)
				return false;
			if (avatar == null) {
				if (other.avatar != null)
					return false;
			} else if (!avatar.equals(other.avatar))
				return false;
			if (id != other.id)
				return false;
			return true;
		}
	}
}
