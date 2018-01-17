package com.example.test.model;

import java.io.Serializable;
import com.easyjson.annotation.JSONClass;
import java.util.ArrayList;
import com.easyjson.annotation.JSONField;

/**
 *
 * @author EasyJson By xinjun
 *
 */
public class Rooms implements Serializable{

	/**
	 * 自动生成的序列化串号
	 */
	private static final long serialVersionUID = 6868098769966667864L;
	/**
	 * 
	 */
	@JSONField("result")
	private Result result;


	//**********************************************Getter and Setter************************************************

	public Result getResult(){
		return this.result;
	}
	public void setResult(Result result){
		this.result=result;
	}


	//**************************************************toString******************************************************

	@Override
	public String toString() {
		return "Rooms [result=" + result + "]";
	}


	//**************************************************equals******************************************************

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rooms other = (Rooms) obj;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}

	/**
	 *
	 * @author EasyJson By xinjun
	 *
	 */
	@JSONClass("result")
	public static class Result implements Serializable{

		/**
		 * 自动生成的序列化串号
		 */
		private static final long serialVersionUID = 6917673287942028185L;
		/**
		 * 
		 */
		private int total;
		/**
		 * 
		 */
		@JSONField("rooms")
		private ArrayList<Room> rooms;
		/**
		 * 
		 */
		private int limit;


		//**********************************************Getter and Setter************************************************

		public int getTotal(){
			return this.total;
		}
		public void setTotal(int total){
			this.total=total;
		}
		public ArrayList<Room> getRooms(){
			return this.rooms;
		}
		public void setRooms(ArrayList<Room> rooms){
			this.rooms=rooms;
		}
		public int getLimit(){
			return this.limit;
		}
		public void setLimit(int limit){
			this.limit=limit;
		}


		//**************************************************toString******************************************************

		@Override
		public String toString() {
			return "Result [total=" + total + ", rooms=" + rooms
					+ ", limit=" + limit + "]";
		}


		//**************************************************equals******************************************************

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Result other = (Result) obj;
			if (total != other.total)
				return false;
			if (rooms == null) {
				if (other.rooms != null && other.rooms.size()!=0)
					return false;
			} else {
				for(int i=0;i<rooms.size();i++)
					if (!rooms.get(i).equals(other.rooms.get(i)))
						return false;
			}
			if (limit != other.limit)
				return false;
			return true;
		}

		/**
		 *
		 * @author EasyJson By xinjun
		 *
		 */
		@JSONClass("rooms")
		public static class Room implements Serializable{

			/**
			 * 自动生成的序列化串号
			 */
			private static final long serialVersionUID = 6907758384346956120L;
			/**
			 * 
			 */
			private int fid;
			/**
			 * 
			 */
			private ArrayList<Integer> vips;
			/**
			 * 
			 */
			private int heartbeat;
			/**
			 * 
			 */
			private int rid;
			/**
			 * 
			 */
			private int allowmic;
			/**
			 * 
			 */
			private String type;
			/**
			 * 
			 */
			private int isonline;
			/**
			 * 
			 */
			private long crtime;
			/**
			 * 
			 */
			private String roomname;
			/**
			 * 
			 */
			private int allowvideo;
			/**
			 * 
			 */
			private String datatype;
			/**
			 * 
			 */
			private int usercount;
			/**
			 * 
			 */
			@JSONField("family_owner")
			private int familyOwner;
			/**
			 * 
			 */
			@JSONField("owner")
			private Owner owner;
			/**
			 * 
			 */
			private int needpwd;
			/**
			 * 
			 */
			@JSONField("onmic_media_type")
			private String onmicMediaType;
			/**
			 * 
			 */
			private String label;
			/**
			 * 
			 */
			@JSONField("anchor")
			private Anchor anchor;
			/**
			 * 
			 */
			private String name;
			/**
			 * 
			 */
			@JSONField("img_path_m")
			private String imgPathM;
			/**
			 * 
			 */
			private int guest;
			/**
			 * 
			 */
			private int micnt;
			/**
			 * 
			 */
			@JSONField("family_level")
			private int familyLevel;
			/**
			 * 
			 */
			private ArrayList<Integer> admins;
			/**
			 * 
			 */
			private ArrayList<Integer> mics;


			//**********************************************Getter and Setter************************************************

			public int getFid(){
				return this.fid;
			}
			public void setFid(int fid){
				this.fid=fid;
			}
			public ArrayList<Integer> getVips(){
				return this.vips;
			}
			public void setVips(ArrayList<Integer> vips){
				this.vips=vips;
			}
			public String getOnmicMediaType(){
				return this.onmicMediaType;
			}
			public void setOnmicMediaType(String onmicMediaType){
				this.onmicMediaType=onmicMediaType;
			}
			public int getHeartbeat(){
				return this.heartbeat;
			}
			public void setHeartbeat(int heartbeat){
				this.heartbeat=heartbeat;
			}
			public int getRid(){
				return this.rid;
			}
			public void setRid(int rid){
				this.rid=rid;
			}
			public int getAllowmic(){
				return this.allowmic;
			}
			public void setAllowmic(int allowmic){
				this.allowmic=allowmic;
			}
			public String getType(){
				return this.type;
			}
			public void setType(String type){
				this.type=type;
			}
			public int getIsonline(){
				return this.isonline;
			}
			public void setIsonline(int isonline){
				this.isonline=isonline;
			}
			public long getCrtime(){
				return this.crtime;
			}
			public void setCrtime(long crtime){
				this.crtime=crtime;
			}
			public String getRoomname(){
				return this.roomname;
			}
			public void setRoomname(String roomname){
				this.roomname=roomname;
			}
			public int getAllowvideo(){
				return this.allowvideo;
			}
			public void setAllowvideo(int allowvideo){
				this.allowvideo=allowvideo;
			}
			public String getDatatype(){
				return this.datatype;
			}
			public void setDatatype(String datatype){
				this.datatype=datatype;
			}
			public int getUsercount(){
				return this.usercount;
			}
			public void setUsercount(int usercount){
				this.usercount=usercount;
			}
			public Owner getOwner(){
				return this.owner;
			}
			public void setOwner(Owner owner){
				this.owner=owner;
			}
			public int getNeedpwd(){
				return this.needpwd;
			}
			public void setNeedpwd(int needpwd){
				this.needpwd=needpwd;
			}
			public String getLabel(){
				return this.label;
			}
			public void setLabel(String label){
				this.label=label;
			}
			public int getFamilyLevel(){
				return this.familyLevel;
			}
			public void setFamilyLevel(int familyLevel){
				this.familyLevel=familyLevel;
			}
			public int getFamilyOwner(){
				return this.familyOwner;
			}
			public void setFamilyOwner(int familyOwner){
				this.familyOwner=familyOwner;
			}
			public Anchor getAnchor(){
				return this.anchor;
			}
			public void setAnchor(Anchor anchor){
				this.anchor=anchor;
			}
			public String getName(){
				return this.name;
			}
			public void setName(String name){
				this.name=name;
			}
			public int getGuest(){
				return this.guest;
			}
			public void setGuest(int guest){
				this.guest=guest;
			}
			public int getMicnt(){
				return this.micnt;
			}
			public void setMicnt(int micnt){
				this.micnt=micnt;
			}
			public ArrayList<Integer> getAdmins(){
				return this.admins;
			}
			public void setAdmins(ArrayList<Integer> admins){
				this.admins=admins;
			}
			public String getImgPathM(){
				return this.imgPathM;
			}
			public void setImgPathM(String imgPathM){
				this.imgPathM=imgPathM;
			}
			public ArrayList<Integer> getMics(){
				return this.mics;
			}
			public void setMics(ArrayList<Integer> mics){
				this.mics=mics;
			}


			//**************************************************toString******************************************************

			@Override
			public String toString() {
				return "Room [fid=" + fid + ", vips=" + vips
						+ ", onmicMediaType=" + onmicMediaType + ", heartbeat=" + heartbeat
						+ ", rid=" + rid + ", allowmic=" + allowmic
						+ ", type=" + type + ", isonline=" + isonline
						+ ", crtime=" + crtime + ", roomname=" + roomname
						+ ", allowvideo=" + allowvideo + ", datatype=" + datatype
						+ ", usercount=" + usercount + ", owner=" + owner
						+ ", needpwd=" + needpwd + ", label=" + label
						+ ", familyLevel=" + familyLevel + ", familyOwner=" + familyOwner
						+ ", anchor=" + anchor + ", name=" + name
						+ ", guest=" + guest + ", micnt=" + micnt
						+ ", admins=" + admins + ", imgPathM=" + imgPathM
						+ ", mics=" + mics + "]";
			}


			//**************************************************equals******************************************************

			@Override
			public boolean equals(Object obj) {
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Room other = (Room) obj;
				if (fid != other.fid)
					return false;
				if (vips == null) {
					if (other.vips != null && other.vips.size()!=0)
						return false;
				} else {
					for(int i=0;i<vips.size();i++)
						if (!vips.get(i).equals(other.vips.get(i)))
							return false;
				}
				if (onmicMediaType == null) {
					if (other.onmicMediaType != null)
						return false;
				} else if (!onmicMediaType.equals(other.onmicMediaType))
					return false;
				if (heartbeat != other.heartbeat)
					return false;
				if (rid != other.rid)
					return false;
				if (allowmic != other.allowmic)
					return false;
				if (type == null) {
					if (other.type != null)
						return false;
				} else if (!type.equals(other.type))
					return false;
				if (isonline != other.isonline)
					return false;
				if (crtime != other.crtime)
					return false;
				if (roomname == null) {
					if (other.roomname != null)
						return false;
				} else if (!roomname.equals(other.roomname))
					return false;
				if (allowvideo != other.allowvideo)
					return false;
				if (datatype == null) {
					if (other.datatype != null)
						return false;
				} else if (!datatype.equals(other.datatype))
					return false;
				if (usercount != other.usercount)
					return false;
				if (owner == null) {
					if (other.owner != null)
						return false;
				} else if (!owner.equals(other.owner))
					return false;
				if (needpwd != other.needpwd)
					return false;
				if (label == null) {
					if (other.label != null)
						return false;
				} else if (!label.equals(other.label))
					return false;
				if (familyLevel != other.familyLevel)
					return false;
				if (familyOwner != other.familyOwner)
					return false;
				if (anchor == null) {
					if (other.anchor != null)
						return false;
				} else if (!anchor.equals(other.anchor))
					return false;
				if (name == null) {
					if (other.name != null)
						return false;
				} else if (!name.equals(other.name))
					return false;
				if (guest != other.guest)
					return false;
				if (micnt != other.micnt)
					return false;
				if (admins == null) {
					if (other.admins != null && other.admins.size()!=0)
						return false;
				} else {
					for(int i=0;i<admins.size();i++)
						if (!admins.get(i).equals(other.admins.get(i)))
							return false;
				}
				if (imgPathM == null) {
					if (other.imgPathM != null)
						return false;
				} else if (!imgPathM.equals(other.imgPathM))
					return false;
				if (mics == null) {
					if (other.mics != null && other.mics.size()!=0)
						return false;
				} else {
					for(int i=0;i<mics.size();i++)
						if (!mics.get(i).equals(other.mics.get(i)))
							return false;
				}
				return true;
			}

			/**
			 *
			 * @author EasyJson By xinjun
			 *
			 */
			@JSONClass("owner")
			public static class Owner implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 6909410867563640249L;
				/**
				 * 
				 */
				private int gender;
				/**
				 * 
				 */
				private int level;
				/**
				 * 
				 */
				private String auth;
				/**
				 * 
				 */
				private String streams;
				/**
				 * 
				 */
				private String icon;
				/**
				 * 
				 */
				private int rid;
				/**
				 * 
				 */
				private int uid;
				/**
				 * 
				 */
				private String face;
				/**
				 * 
				 */
				private String name;
				/**
				 * 
				 */
				private String nickname;
				/**
				 * 
				 */
				private String levelname;
				/**
				 * 
				 */
				@JSONField("family")
				private Family family;
				/**
				 * 
				 */
				private String vip;
				/**
				 * 
				 */
				private String full;


				//**********************************************Getter and Setter************************************************

				public int getGender(){
					return this.gender;
				}
				public void setGender(int gender){
					this.gender=gender;
				}
				public int getLevel(){
					return this.level;
				}
				public void setLevel(int level){
					this.level=level;
				}
				public String getAuth(){
					return this.auth;
				}
				public void setAuth(String auth){
					this.auth=auth;
				}
				public String getStreams(){
					return this.streams;
				}
				public void setStreams(String streams){
					this.streams=streams;
				}
				public String getIcon(){
					return this.icon;
				}
				public void setIcon(String icon){
					this.icon=icon;
				}
				public int getRid(){
					return this.rid;
				}
				public void setRid(int rid){
					this.rid=rid;
				}
				public int getUid(){
					return this.uid;
				}
				public void setUid(int uid){
					this.uid=uid;
				}
				public String getFace(){
					return this.face;
				}
				public void setFace(String face){
					this.face=face;
				}
				public String getName(){
					return this.name;
				}
				public void setName(String name){
					this.name=name;
				}
				public String getNickname(){
					return this.nickname;
				}
				public void setNickname(String nickname){
					this.nickname=nickname;
				}
				public String getLevelname(){
					return this.levelname;
				}
				public void setLevelname(String levelname){
					this.levelname=levelname;
				}
				public Family getFamily(){
					return this.family;
				}
				public void setFamily(Family family){
					this.family=family;
				}
				public String getVip(){
					return this.vip;
				}
				public void setVip(String vip){
					this.vip=vip;
				}
				public String getFull(){
					return this.full;
				}
				public void setFull(String full){
					this.full=full;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Owner [gender=" + gender + ", level=" + level
							+ ", auth=" + auth + ", streams=" + streams
							+ ", icon=" + icon + ", rid=" + rid
							+ ", uid=" + uid + ", face=" + face
							+ ", name=" + name + ", nickname=" + nickname
							+ ", levelname=" + levelname + ", family=" + family
							+ ", vip=" + vip + ", full=" + full
							+ "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Owner other = (Owner) obj;
					if (gender != other.gender)
						return false;
					if (level != other.level)
						return false;
					if (auth == null) {
						if (other.auth != null)
							return false;
					} else if (!auth.equals(other.auth))
						return false;
					if (streams == null) {
						if (other.streams != null)
							return false;
					} else if (!streams.equals(other.streams))
						return false;
					if (icon == null) {
						if (other.icon != null)
							return false;
					} else if (!icon.equals(other.icon))
						return false;
					if (rid != other.rid)
						return false;
					if (uid != other.uid)
						return false;
					if (face == null) {
						if (other.face != null)
							return false;
					} else if (!face.equals(other.face))
						return false;
					if (name == null) {
						if (other.name != null)
							return false;
					} else if (!name.equals(other.name))
						return false;
					if (nickname == null) {
						if (other.nickname != null)
							return false;
					} else if (!nickname.equals(other.nickname))
						return false;
					if (levelname == null) {
						if (other.levelname != null)
							return false;
					} else if (!levelname.equals(other.levelname))
						return false;
					if (family == null) {
						if (other.family != null)
							return false;
					} else if (!family.equals(other.family))
						return false;
					if (vip == null) {
						if (other.vip != null)
							return false;
					} else if (!vip.equals(other.vip))
						return false;
					if (full == null) {
						if (other.full != null)
							return false;
					} else if (!full.equals(other.full))
						return false;
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("family")
				public static class Family implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 6899495963968568184L;
					/**
					 * 
					 */
					private int fid;
					/**
					 * 
					 */
					@JSONField("family_name")
					private String familyName;
					/**
					 * 
					 */
					@JSONField("family_owner")
					private int familyOwner;


					//**********************************************Getter and Setter************************************************

					public int getFid(){
						return this.fid;
					}
					public void setFid(int fid){
						this.fid=fid;
					}
					public int getFamilyOwner(){
						return this.familyOwner;
					}
					public void setFamilyOwner(int familyOwner){
						this.familyOwner=familyOwner;
					}
					public String getFamilyName(){
						return this.familyName;
					}
					public void setFamilyName(String familyName){
						this.familyName=familyName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Family [fid=" + fid + ", familyOwner=" + familyOwner
								+ ", familyName=" + familyName + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Family other = (Family) obj;
						if (fid != other.fid)
							return false;
						if (familyOwner != other.familyOwner)
							return false;
						if (familyName == null) {
							if (other.familyName != null)
								return false;
						} else if (!familyName.equals(other.familyName))
							return false;
						return true;
					}
				}
			}

			/**
			 *
			 * @author EasyJson By xinjun
			 *
			 */
			@JSONClass("anchor")
			public static class Anchor implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 6836701575964767543L;
				/**
				 * 
				 */
				private int gender;
				/**
				 * 
				 */
				private int level;
				/**
				 * 
				 */
				private String streams;
				/**
				 * 
				 */
				private String mt;
				/**
				 * 
				 */
				private String media;
				/**
				 * 
				 */
				private int rid;
				/**
				 * 
				 */
				private int uid;
				/**
				 * 
				 */
				private String face;
				/**
				 * 
				 */
				@JSONField("banzou")
				private Banzou banzou;
				/**
				 * 
				 */
				private String name;
				/**
				 * 
				 */
				private String levelname;
				/**
				 * 
				 */
				@JSONField("family")
				private Family family;
				/**
				 * 
				 */
				private String full;


				//**********************************************Getter and Setter************************************************

				public int getGender(){
					return this.gender;
				}
				public void setGender(int gender){
					this.gender=gender;
				}
				public int getLevel(){
					return this.level;
				}
				public void setLevel(int level){
					this.level=level;
				}
				public String getStreams(){
					return this.streams;
				}
				public void setStreams(String streams){
					this.streams=streams;
				}
				public String getMt(){
					return this.mt;
				}
				public void setMt(String mt){
					this.mt=mt;
				}
				public String getMedia(){
					return this.media;
				}
				public void setMedia(String media){
					this.media=media;
				}
				public int getRid(){
					return this.rid;
				}
				public void setRid(int rid){
					this.rid=rid;
				}
				public int getUid(){
					return this.uid;
				}
				public void setUid(int uid){
					this.uid=uid;
				}
				public String getFace(){
					return this.face;
				}
				public void setFace(String face){
					this.face=face;
				}
				public Banzou getBanzou(){
					return this.banzou;
				}
				public void setBanzou(Banzou banzou){
					this.banzou=banzou;
				}
				public String getName(){
					return this.name;
				}
				public void setName(String name){
					this.name=name;
				}
				public String getLevelname(){
					return this.levelname;
				}
				public void setLevelname(String levelname){
					this.levelname=levelname;
				}
				public Family getFamily(){
					return this.family;
				}
				public void setFamily(Family family){
					this.family=family;
				}
				public String getFull(){
					return this.full;
				}
				public void setFull(String full){
					this.full=full;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Anchor [gender=" + gender + ", level=" + level
							+ ", streams=" + streams + ", mt=" + mt
							+ ", media=" + media + ", rid=" + rid
							+ ", uid=" + uid + ", face=" + face
							+ ", banzou=" + banzou + ", name=" + name
							+ ", levelname=" + levelname + ", family=" + family
							+ ", full=" + full + "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Anchor other = (Anchor) obj;
					if (gender != other.gender)
						return false;
					if (level != other.level)
						return false;
					if (streams == null) {
						if (other.streams != null)
							return false;
					} else if (!streams.equals(other.streams))
						return false;
					if (mt == null) {
						if (other.mt != null)
							return false;
					} else if (!mt.equals(other.mt))
						return false;
					if (media == null) {
						if (other.media != null)
							return false;
					} else if (!media.equals(other.media))
						return false;
					if (rid != other.rid)
						return false;
					if (uid != other.uid)
						return false;
					if (face == null) {
						if (other.face != null)
							return false;
					} else if (!face.equals(other.face))
						return false;
					if (banzou == null) {
						if (other.banzou != null)
							return false;
					} else if (!banzou.equals(other.banzou))
						return false;
					if (name == null) {
						if (other.name != null)
							return false;
					} else if (!name.equals(other.name))
						return false;
					if (levelname == null) {
						if (other.levelname != null)
							return false;
					} else if (!levelname.equals(other.levelname))
						return false;
					if (family == null) {
						if (other.family != null)
							return false;
					} else if (!family.equals(other.family))
						return false;
					if (full == null) {
						if (other.full != null)
							return false;
					} else if (!full.equals(other.full))
						return false;
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("banzou")
				public static class Banzou implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 6828439155586379607L;
					/**
					 * 
					 */
					private String singer;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					private int bzid;
					/**
					 * 
					 */
					private int bztime;


					//**********************************************Getter and Setter************************************************

					public String getSinger(){
						return this.singer;
					}
					public void setSinger(String singer){
						this.singer=singer;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getBzid(){
						return this.bzid;
					}
					public void setBzid(int bzid){
						this.bzid=bzid;
					}
					public int getBztime(){
						return this.bztime;
					}
					public void setBztime(int bztime){
						this.bztime=bztime;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Banzou [singer=" + singer + ", name=" + name
								+ ", bzid=" + bzid + ", bztime=" + bztime
								+ "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Banzou other = (Banzou) obj;
						if (singer == null) {
							if (other.singer != null)
								return false;
						} else if (!singer.equals(other.singer))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (bzid != other.bzid)
							return false;
						if (bztime != other.bztime)
							return false;
						return true;
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("family")
				public static class Family implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 6821829218424675798L;
					/**
					 * 
					 */
					private int fid;
					/**
					 * 
					 */
					@JSONField("family_name")
					private String familyName;
					/**
					 * 
					 */
					@JSONField("family_owner")
					private int familyOwner;


					//**********************************************Getter and Setter************************************************

					public int getFid(){
						return this.fid;
					}
					public void setFid(int fid){
						this.fid=fid;
					}
					public int getFamilyOwner(){
						return this.familyOwner;
					}
					public void setFamilyOwner(int familyOwner){
						this.familyOwner=familyOwner;
					}
					public String getFamilyName(){
						return this.familyName;
					}
					public void setFamilyName(String familyName){
						this.familyName=familyName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Family [fid=" + fid + ", familyOwner=" + familyOwner
								+ ", familyName=" + familyName + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Family other = (Family) obj;
						if (fid != other.fid)
							return false;
						if (familyOwner != other.familyOwner)
							return false;
						if (familyName == null) {
							if (other.familyName != null)
								return false;
						} else if (!familyName.equals(other.familyName))
							return false;
						return true;
					}
				}
			}
		}
	}
}
