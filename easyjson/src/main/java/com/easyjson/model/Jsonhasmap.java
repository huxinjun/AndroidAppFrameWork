package com.easyjson.model;


import java.io.Serializable;
import com.easyjson.annotation.JSONClass;
import java.util.HashMap;
import com.easyjson.annotation.JSONField;
import java.util.ArrayList;
/**
 *
 * @author EasyJson By xinjun
 *
 */
public class Jsonhasmap implements Serializable{

	/**
	 *自动生成的序列化串号
	 */
	private static final long serialVersionUID = -1291787774939899158L;
	/**
	 *
	 */
	private int errorCode;
	/**
	 *
	 */
	@JSONField("items")
	private ArrayList<Item> items;
	/**
	 *
	 */
	private String errorMsg;


	//**********************************************Getter and Setter************************************************

	public int getErrorCode(){
		return this.errorCode;
	}
	public void setErrorCode(int errorCode){
		this.errorCode=errorCode;
	}
	public ArrayList<Item> getItems(){
		return this.items;
	}
	public void setItems(ArrayList<Item> items){
		this.items=items;
	}
	public String getErrorMsg(){
		return this.errorMsg;
	}
	public void setErrorMsg(String errorMsg){
		this.errorMsg=errorMsg;
	}


	//**************************************************toString******************************************************

	@Override
	public String toString() {
		return "Jsonhasmap [errorCode=" + errorCode + ", items=" + items
				+ ", errorMsg=" + errorMsg + "]";
	}


	//**************************************************equals******************************************************

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jsonhasmap other = (Jsonhasmap) obj;
		if (errorCode != other.errorCode)
			return false;
		if (items == null) {
			if (other.items != null && other.items.size()!=0)
				return false;
		} else {
			for(int i=0;i<items.size();i++)
				if (!items.get(i).equals(other.items.get(i)))
					return false;
		}
		if (errorMsg == null) {
			if (other.errorMsg != null)
				return false;
		} else if (!errorMsg.equals(other.errorMsg))
			return false;
		return true;
	}

	/**
	 *
	 * @author EasyJson By xinjun
	 *
	 */
	@JSONClass("items")
	public static class Item implements Serializable{

		/**
		 *自动生成的序列化串号
		 */
		private static final long serialVersionUID = -943113661600725485L;
		/**
		 *
		 */
		private String consultdate;
		/**
		 *
		 */
		private String datatype;
		/**
		 *
		 */
		private HashMap<String,Integer> datas;


		//**********************************************Getter and Setter************************************************

		public String getConsultdate(){
			return this.consultdate;
		}
		public void setConsultdate(String consultdate){
			this.consultdate=consultdate;
		}
		public String getDatatype(){
			return this.datatype;
		}
		public void setDatatype(String datatype){
			this.datatype=datatype;
		}
		public HashMap<String,Integer> getDatas(){
			return this.datas;
		}
		public void setDatas(HashMap<String,Integer> datas){
			this.datas=datas;
		}


		//**************************************************toString******************************************************

		@Override
		public String toString() {
			return "Item [consultdate=" + consultdate + ", datatype=" + datatype
					+ ", datas=" + datas + "]";
		}


		//**************************************************equals******************************************************

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Item other = (Item) obj;
			if (consultdate == null) {
				if (other.consultdate != null)
					return false;
			} else if (!consultdate.equals(other.consultdate))
				return false;
			if (datatype == null) {
				if (other.datatype != null)
					return false;
			} else if (!datatype.equals(other.datatype))
				return false;
			if (datas == null) {
				if (other.datas != null)
					return false;
			} else if (!datas.equals(other.datas))
				return false;
			return true;
		}
	}
}
