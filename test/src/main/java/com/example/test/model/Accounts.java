package com.example.test.model;

import java.io.Serializable;
import com.easyjson.annotation.JSONClass;
import com.easyjson.annotation.JSONField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EasyJson By xinjun
 *
 */
public class Accounts implements Serializable{

	/**
	 * 自动生成的序列化串号
	 */
	private static final long serialVersionUID = 7680232383896377070L;
	/**
	 * 
	 */
	private String msg;
	/**
	 * 
	 */
	private boolean hasNextPage;
	/**
	 * 
	 */
	private int pageIndex;
	/**
	 * 
	 */
	private int pageSize;
	/**
	 * 
	 */
	@JSONField("accounts")
	private ArrayList<Account> accounts;
	/**
	 * 
	 */
	private int status;


	//**********************************************Getter and Setter************************************************

	public String getMsg(){
		return this.msg;
	}
	public void setMsg(String msg){
		this.msg=msg;
	}
	public boolean getHasNextPage(){
		return this.hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage){
		this.hasNextPage=hasNextPage;
	}
	public int getPageIndex(){
		return this.pageIndex;
	}
	public void setPageIndex(int pageIndex){
		this.pageIndex=pageIndex;
	}
	public int getPageSize(){
		return this.pageSize;
	}
	public void setPageSize(int pageSize){
		this.pageSize=pageSize;
	}
	public ArrayList<Account> getAccounts(){
		return this.accounts;
	}
	public void setAccounts(ArrayList<Account> accounts){
		this.accounts=accounts;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int status){
		this.status=status;
	}


	//**************************************************toString******************************************************

	@Override
	public String toString() {
		return "Accounts [msg=" + msg + ", hasNextPage=" + hasNextPage
				+ ", pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", accounts=" + accounts + ", status=" + status
				+ "]";
	}


	//**************************************************equals******************************************************

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accounts other = (Accounts) obj;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (hasNextPage != other.hasNextPage)
			return false;
		if (pageIndex != other.pageIndex)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (accounts == null) {
			if (other.accounts != null && other.accounts.size()!=0)
				return false;
		} else {
			for(int i=0;i<accounts.size();i++)
				if (!accounts.get(i).equals(other.accounts.get(i)))
					return false;
		}
		if (status != other.status)
			return false;
		return true;
	}

	/**
	 *
	 * @author EasyJson By xinjun
	 *
	 */
	@JSONClass("accounts")
	public static class Account implements Serializable{

		/**
		 * 自动生成的序列化串号
		 */
		private static final long serialVersionUID = 7670317480301305005L;
		/**
		 * 
		 */
		private String date;
		/**
		 * 
		 */
		private ArrayList<String> imgs;
		/**
		 * 
		 */
		private String dateDis;
		/**
		 * 
		 */
		private String description;
		/**
		 * 
		 */
		private boolean isPrivate;
		/**
		 * 
		 */
		private String type;
		/**
		 * 
		 */
		private float addrLon;
		/**
		 * 
		 */
		private String addrName;
		/**
		 * 
		 */
		private String userId;
		/**
		 * 
		 */
		private String bookId;
		/**
		 * 
		 */
		private long createTimestamp;
		/**
		 * 
		 */
		@JSONField("pay_result")
		private ArrayList<PayResult> payResult;
		/**
		 * 
		 */
		private float paidIn;
		/**
		 * 
		 */
		@JSONField("members")
		private ArrayList<Member> members;
		/**
		 * 
		 */
		private String name;
		/**
		 * 
		 */
		private float addrLat;
		/**
		 * 
		 */
		@JSONField("user_icon")
		private String userIcon;
		/**
		 * 
		 */
		private String id;
		/**
		 * 
		 */
		private long dateTimestamp;
		/**
		 * 
		 */
		private String addr;


		//**********************************************Getter and Setter************************************************

		public String getDate(){
			return this.date;
		}
		public void setDate(String date){
			this.date=date;
		}
		public ArrayList<String> getImgs(){
			return this.imgs;
		}
		public void setImgs(ArrayList<String> imgs){
			this.imgs=imgs;
		}
		public ArrayList<PayResult> getPayResult(){
			return this.payResult;
		}
		public void setPayResult(ArrayList<PayResult> payResult){
			this.payResult=payResult;
		}
		public String getDateDis(){
			return this.dateDis;
		}
		public void setDateDis(String dateDis){
			this.dateDis=dateDis;
		}
		public String getDescription(){
			return this.description;
		}
		public void setDescription(String description){
			this.description=description;
		}
		public boolean getIsPrivate(){
			return this.isPrivate;
		}
		public void setIsPrivate(boolean isPrivate){
			this.isPrivate=isPrivate;
		}
		public String getType(){
			return this.type;
		}
		public void setType(String type){
			this.type=type;
		}
		public float getAddrLon(){
			return this.addrLon;
		}
		public void setAddrLon(float addrLon){
			this.addrLon=addrLon;
		}
		public String getAddrName(){
			return this.addrName;
		}
		public void setAddrName(String addrName){
			this.addrName=addrName;
		}
		public String getUserId(){
			return this.userId;
		}
		public void setUserId(String userId){
			this.userId=userId;
		}
		public String getBookId(){
			return this.bookId;
		}
		public void setBookId(String bookId){
			this.bookId=bookId;
		}
		public long getCreateTimestamp(){
			return this.createTimestamp;
		}
		public void setCreateTimestamp(long createTimestamp){
			this.createTimestamp=createTimestamp;
		}
		public float getPaidIn(){
			return this.paidIn;
		}
		public void setPaidIn(float paidIn){
			this.paidIn=paidIn;
		}
		public ArrayList<Member> getMembers(){
			return this.members;
		}
		public void setMembers(ArrayList<Member> members){
			this.members=members;
		}
		public String getName(){
			return this.name;
		}
		public void setName(String name){
			this.name=name;
		}
		public float getAddrLat(){
			return this.addrLat;
		}
		public void setAddrLat(float addrLat){
			this.addrLat=addrLat;
		}
		public String getId(){
			return this.id;
		}
		public void setId(String id){
			this.id=id;
		}
		public String getUserIcon(){
			return this.userIcon;
		}
		public void setUserIcon(String userIcon){
			this.userIcon=userIcon;
		}
		public long getDateTimestamp(){
			return this.dateTimestamp;
		}
		public void setDateTimestamp(long dateTimestamp){
			this.dateTimestamp=dateTimestamp;
		}
		public String getAddr(){
			return this.addr;
		}
		public void setAddr(String addr){
			this.addr=addr;
		}


		//**************************************************toString******************************************************

		@Override
		public String toString() {
			return "Account [date=" + date + ", imgs=" + imgs
					+ ", payResult=" + payResult + ", dateDis=" + dateDis
					+ ", description=" + description + ", isPrivate=" + isPrivate
					+ ", type=" + type + ", addrLon=" + addrLon
					+ ", addrName=" + addrName + ", userId=" + userId
					+ ", bookId=" + bookId + ", createTimestamp=" + createTimestamp
					+ ", paidIn=" + paidIn + ", members=" + members
					+ ", name=" + name + ", addrLat=" + addrLat
					+ ", id=" + id + ", userIcon=" + userIcon
					+ ", dateTimestamp=" + dateTimestamp + ", addr=" + addr
					+ "]";
		}


		//**************************************************equals******************************************************

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Account other = (Account) obj;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (imgs == null) {
				if (other.imgs != null)
					return false;
			} else if (!imgs.equals(other.imgs))
				return false;
			if (payResult == null) {
				if (other.payResult != null && other.payResult.size()!=0)
					return false;
			} else {
				for(int i=0;i<payResult.size();i++)
					if (!payResult.get(i).equals(other.payResult.get(i)))
						return false;
			}
			if (dateDis == null) {
				if (other.dateDis != null)
					return false;
			} else if (!dateDis.equals(other.dateDis))
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (isPrivate != other.isPrivate)
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			if (addrLon != other.addrLon)
				return false;
			if (addrName == null) {
				if (other.addrName != null)
					return false;
			} else if (!addrName.equals(other.addrName))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			if (bookId == null) {
				if (other.bookId != null)
					return false;
			} else if (!bookId.equals(other.bookId))
				return false;
			if (createTimestamp != other.createTimestamp)
				return false;
			if (paidIn != other.paidIn)
				return false;
			if (members == null) {
				if (other.members != null && other.members.size()!=0)
					return false;
			} else {
				for(int i=0;i<members.size();i++)
					if (!members.get(i).equals(other.members.get(i)))
						return false;
			}
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (addrLat != other.addrLat)
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (userIcon == null) {
				if (other.userIcon != null)
					return false;
			} else if (!userIcon.equals(other.userIcon))
				return false;
			if (dateTimestamp != other.dateTimestamp)
				return false;
			if (addr == null) {
				if (other.addr != null)
					return false;
			} else if (!addr.equals(other.addr))
				return false;
			return true;
		}

		/**
		 *
		 * @author EasyJson By xinjun
		 *
		 */
		@JSONClass("pay_result")
		public static class PayResult implements Serializable{

			/**
			 * 自动生成的序列化串号
			 */
			private static final long serialVersionUID = 7675274934246324686L;
			/**
			 * 付账计算结果
			 */
			@JSONField("pay_target")
			private ArrayList<PayTarget> payTarget;


			//**********************************************Getter and Setter************************************************

			public ArrayList<PayTarget> getPayTarget(){
				return this.payTarget;
			}
			public void setPayTarget(ArrayList<PayTarget> payTarget){
				this.payTarget=payTarget;
			}


			//**************************************************toString******************************************************

			@Override
			public String toString() {
				return "PayResult [payTarget=" + payTarget + "]";
			}


			//**************************************************equals******************************************************

			@Override
			public boolean equals(Object obj) {
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				PayResult other = (PayResult) obj;
				if (payTarget == null) {
					if (other.payTarget != null && other.payTarget.size()!=0)
						return false;
				} else {
					for(int i=0;i<payTarget.size();i++)
						if (!payTarget.get(i).equals(other.payTarget.get(i)))
							return false;
				}
				return true;
			}

			/**
			 *
			 * publicstaticfinalintSTATUS_COMPLETED=2;已经完善账单<br>
			 * @author EasyJson By xinjun
			 *
			 */
			@JSONClass("pay_target")
			public static class PayTarget implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 7675274934246324686L;
				/**
				 * 被抵消的次数
				 */
				@JSONField("receipt_status")
				private int receiptStatus;
				/**
				 * 谁支付
				 */
				@JSONField("account_id")
				private String accountId;
				/**
				 * 收款者状态
				 */
				@JSONField("paid_status")
				private int paidStatus;
				/**
				 * 待付金额
				 */
				private float money;
				/**
				 * 
				 */
				@JSONField("offset_money")
				private float offsetMoney;
				/**
				 * 被抵消的金额
				 */
				@JSONField("offset_count")
				private int offsetCount;
				/**
				 * 给谁支付
				 */
				@JSONField("paid_id")
				private String paidId;
				/**
				 * 支付多少
				 */
				@JSONField("receipt_id")
				private String receiptId;
				/**
				 * 支付者状态
				 */
				private float waitPaidMoney;
				/**
				 * 
				 */
				private String id;


				//**********************************************Getter and Setter************************************************

				public String getAccountId(){
					return this.accountId;
				}
				public void setAccountId(String accountId){
					this.accountId=accountId;
				}
				public String getPaidId(){
					return this.paidId;
				}
				public void setPaidId(String paidId){
					this.paidId=paidId;
				}
				public int getPaidStatus(){
					return this.paidStatus;
				}
				public void setPaidStatus(int paidStatus){
					this.paidStatus=paidStatus;
				}
				public float getMoney(){
					return this.money;
				}
				public void setMoney(float money){
					this.money=money;
				}
				public float getWaitPaidMoney(){
					return this.waitPaidMoney;
				}
				public void setWaitPaidMoney(float waitPaidMoney){
					this.waitPaidMoney=waitPaidMoney;
				}
				public String getId(){
					return this.id;
				}
				public void setId(String id){
					this.id=id;
				}
				public float getOffsetMoney(){
					return this.offsetMoney;
				}
				public void setOffsetMoney(float offsetMoney){
					this.offsetMoney=offsetMoney;
				}
				public String getReceiptId(){
					return this.receiptId;
				}
				public void setReceiptId(String receiptId){
					this.receiptId=receiptId;
				}
				public int getReceiptStatus(){
					return this.receiptStatus;
				}
				public void setReceiptStatus(int receiptStatus){
					this.receiptStatus=receiptStatus;
				}
				public int getOffsetCount(){
					return this.offsetCount;
				}
				public void setOffsetCount(int offsetCount){
					this.offsetCount=offsetCount;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "PayTarget [accountId=" + accountId + ", paidId=" + paidId
							+ ", paidStatus=" + paidStatus + ", money=" + money
							+ ", waitPaidMoney=" + waitPaidMoney + ", id=" + id
							+ ", offsetMoney=" + offsetMoney + ", receiptId=" + receiptId
							+ ", receiptStatus=" + receiptStatus + ", offsetCount=" + offsetCount
							+ "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					PayTarget other = (PayTarget) obj;
					if (accountId == null) {
						if (other.accountId != null)
							return false;
					} else if (!accountId.equals(other.accountId))
						return false;
					if (paidId == null) {
						if (other.paidId != null)
							return false;
					} else if (!paidId.equals(other.paidId))
						return false;
					if (paidStatus != other.paidStatus)
						return false;
					if (money != other.money)
						return false;
					if (waitPaidMoney != other.waitPaidMoney)
						return false;
					if (id == null) {
						if (other.id != null)
							return false;
					} else if (!id.equals(other.id))
						return false;
					if (offsetMoney != other.offsetMoney)
						return false;
					if (receiptId == null) {
						if (other.receiptId != null)
							return false;
					} else if (!receiptId.equals(other.receiptId))
						return false;
					if (receiptStatus != other.receiptStatus)
						return false;
					if (offsetCount != other.offsetCount)
						return false;
					return true;
				}
			}
		}

		/**
		 *
		 * publicstaticfinalintRULE_TYPE_NUMBER=2;固定数额<br>
		 * @author EasyJson By xinjun
		 *
		 */
		@JSONClass("members")
		public static class Member implements Serializable{

			/**
			 * 自动生成的序列化串号
			 */
			private static final long serialVersionUID = 7668664997084620877L;
			/**
			 * 
			 */
			@JSONField("member_id")
			private String memberId;
			/**
			 * 应付
			 */
			@JSONField("should_pay")
			private float shouldPay;
			/**
			 * 给自己买东西的钱,其他人不会AA支付这个数额
			 */
			@JSONField("rule_num")
			private float ruleNum;
			/**
			 * 如果此成员是组中的,那么这个属性标识父成员的id,否则该成员为账单下的,该字段为null
			 */
			private String parentMemberId;
			/**
			 * 
			 */
			@JSONField("member_icon")
			private String memberIcon;
			/**
			 * 当成员为组时,这个字段标识当前用户是否是组内的成员
			 */
			private boolean isMember;
			/**
			 * 
			 */
			@JSONField("member_name")
			private String memberName;
			/**
			 * 实付
			 */
			@JSONField("paid_in")
			private float paidIn;
			/**
			 * 
			 */
			@JSONField("is_group")
			private boolean isGroup;
			/**
			 * 
			 */
			@JSONField("money_for_self")
			private float moneyForSelf;
			/**
			 * 计算时的临时数据
			 */
			private float calcData;
			/**
			 * 
			 */
			@JSONField("account_id")
			private String accountId;
			/**
			 * 0:基于总支出的百分比的值1:缴费为一个固定值
			 */
			@JSONField("rule_type")
			private int ruleType;
			/**
			 * 
			 */
			private String id;


			//**********************************************Getter and Setter************************************************

			public float getMoneyForSelf(){
				return this.moneyForSelf;
			}
			public void setMoneyForSelf(float moneyForSelf){
				this.moneyForSelf=moneyForSelf;
			}
			public float getRuleNum(){
				return this.ruleNum;
			}
			public void setRuleNum(float ruleNum){
				this.ruleNum=ruleNum;
			}
			public float getShouldPay(){
				return this.shouldPay;
			}
			public void setShouldPay(float shouldPay){
				this.shouldPay=shouldPay;
			}
			public String getParentMemberId(){
				return this.parentMemberId;
			}
			public void setParentMemberId(String parentMemberId){
				this.parentMemberId=parentMemberId;
			}
			public String getMemberName(){
				return this.memberName;
			}
			public void setMemberName(String memberName){
				this.memberName=memberName;
			}
			public boolean getIsMember(){
				return this.isMember;
			}
			public void setIsMember(boolean isMember){
				this.isMember=isMember;
			}
			public float getCalcData(){
				return this.calcData;
			}
			public void setCalcData(float calcData){
				this.calcData=calcData;
			}
			public String getAccountId(){
				return this.accountId;
			}
			public void setAccountId(String accountId){
				this.accountId=accountId;
			}
			public float getPaidIn(){
				return this.paidIn;
			}
			public void setPaidIn(float paidIn){
				this.paidIn=paidIn;
			}
			public int getRuleType(){
				return this.ruleType;
			}
			public void setRuleType(int ruleType){
				this.ruleType=ruleType;
			}
			public String getMemberIcon(){
				return this.memberIcon;
			}
			public void setMemberIcon(String memberIcon){
				this.memberIcon=memberIcon;
			}
			public String getId(){
				return this.id;
			}
			public void setId(String id){
				this.id=id;
			}
			public boolean getIsGroup(){
				return this.isGroup;
			}
			public void setIsGroup(boolean isGroup){
				this.isGroup=isGroup;
			}
			public String getMemberId(){
				return this.memberId;
			}
			public void setMemberId(String memberId){
				this.memberId=memberId;
			}


			//**************************************************toString******************************************************

			@Override
			public String toString() {
				return "Member [moneyForSelf=" + moneyForSelf + ", ruleNum=" + ruleNum
						+ ", shouldPay=" + shouldPay + ", parentMemberId=" + parentMemberId
						+ ", memberName=" + memberName + ", isMember=" + isMember
						+ ", calcData=" + calcData + ", accountId=" + accountId
						+ ", paidIn=" + paidIn + ", ruleType=" + ruleType
						+ ", memberIcon=" + memberIcon + ", id=" + id
						+ ", isGroup=" + isGroup + ", memberId=" + memberId
						+ "]";
			}


			//**************************************************equals******************************************************

			@Override
			public boolean equals(Object obj) {
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Member other = (Member) obj;
				if (moneyForSelf != other.moneyForSelf)
					return false;
				if (ruleNum != other.ruleNum)
					return false;
				if (shouldPay != other.shouldPay)
					return false;
				if (parentMemberId == null) {
					if (other.parentMemberId != null)
						return false;
				} else if (!parentMemberId.equals(other.parentMemberId))
					return false;
				if (memberName == null) {
					if (other.memberName != null)
						return false;
				} else if (!memberName.equals(other.memberName))
					return false;
				if (isMember != other.isMember)
					return false;
				if (calcData != other.calcData)
					return false;
				if (accountId == null) {
					if (other.accountId != null)
						return false;
				} else if (!accountId.equals(other.accountId))
					return false;
				if (paidIn != other.paidIn)
					return false;
				if (ruleType != other.ruleType)
					return false;
				if (memberIcon == null) {
					if (other.memberIcon != null)
						return false;
				} else if (!memberIcon.equals(other.memberIcon))
					return false;
				if (id == null) {
					if (other.id != null)
						return false;
				} else if (!id.equals(other.id))
					return false;
				if (isGroup != other.isGroup)
					return false;
				if (memberId == null) {
					if (other.memberId != null)
						return false;
				} else if (!memberId.equals(other.memberId))
					return false;
				return true;
			}
		}
	}
}
