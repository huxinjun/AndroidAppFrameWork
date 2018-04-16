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
public class Json_1546 implements Serializable{

	/**
	 * 自动生成的序列化串号
	 */
	private static final long serialVersionUID = -9167801190615844278L;
	/**
	 * 
	 */
	@JSONField("milky_Way")
	private MilkyWay milkyWay;


	//**********************************************Getter and Setter************************************************

	public MilkyWay getMilkyWay(){
		return this.milkyWay;
	}
	public void setMilkyWay(MilkyWay milkyWay){
		this.milkyWay=milkyWay;
	}


	//**************************************************toString******************************************************

	@Override
	public String toString() {
		return "Json_1546 [milkyWay=" + milkyWay + "]";
	}


	//**************************************************equals******************************************************

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Json_1546 other = (Json_1546) obj;
		if (milkyWay == null) {
			if (other.milkyWay != null)
				return false;
		} else if (!milkyWay.equals(other.milkyWay))
			return false;
		return true;
	}

	/**
	 *
	 * @author EasyJson By xinjun
	 *
	 */
	@JSONClass("milky_Way")
	public static class MilkyWay implements Serializable{

		/**
		 * 自动生成的序列化串号
		 */
		private static final long serialVersionUID = 9164921493897862248L;
		/**
		 * 
		 */
		@JSONField("solar_system")
		private Solarystem solarSystem;


		//**********************************************Getter and Setter************************************************

		public Solarystem getSolarSystem(){
			return this.solarSystem;
		}
		public void setSolarSystem(Solarystem solarSystem){
			this.solarSystem=solarSystem;
		}


		//**************************************************toString******************************************************

		@Override
		public String toString() {
			return "MilkyWay [solarSystem=" + solarSystem + "]";
		}


		//**************************************************equals******************************************************

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MilkyWay other = (MilkyWay) obj;
			if (solarSystem == null) {
				if (other.solarSystem != null)
					return false;
			} else if (!solarSystem.equals(other.solarSystem))
				return false;
			return true;
		}

		/**
		 *
		 * @author EasyJson By xinjun
		 *
		 */
		@JSONClass("solar_system")
		public static class Solarystem implements Serializable{

			/**
			 * 自动生成的序列化串号
			 */
			private static final long serialVersionUID = 9191361233954742888L;
			/**
			 * 
			 */
			@JSONField("saturn")
			private Saturn saturn;
			/**
			 * 
			 */
			@JSONField("venus")
			private Venu venus;
			/**
			 * 
			 */
			@JSONField("uranus")
			private Uranu uranus;
			/**
			 * 
			 */
			@JSONField("mercury")
			private Mercury mercury;
			/**
			 * 
			 */
			@JSONField("mars")
			private Mar mars;
			/**
			 * 
			 */
			@JSONField("neptune")
			private Neptune neptune;
			/**
			 * 
			 */
			@JSONField("earth")
			private Earth earth;


			//**********************************************Getter and Setter************************************************

			public Saturn getSaturn(){
				return this.saturn;
			}
			public void setSaturn(Saturn saturn){
				this.saturn=saturn;
			}
			public Venu getVenus(){
				return this.venus;
			}
			public void setVenus(Venu venus){
				this.venus=venus;
			}
			public Uranu getUranus(){
				return this.uranus;
			}
			public void setUranus(Uranu uranus){
				this.uranus=uranus;
			}
			public Mercury getMercury(){
				return this.mercury;
			}
			public void setMercury(Mercury mercury){
				this.mercury=mercury;
			}
			public Mar getMars(){
				return this.mars;
			}
			public void setMars(Mar mars){
				this.mars=mars;
			}
			public Neptune getNeptune(){
				return this.neptune;
			}
			public void setNeptune(Neptune neptune){
				this.neptune=neptune;
			}
			public Earth getEarth(){
				return this.earth;
			}
			public void setEarth(Earth earth){
				this.earth=earth;
			}


			//**************************************************toString******************************************************

			@Override
			public String toString() {
				return "Solarystem [saturn=" + saturn + ", venus=" + venus
						+ ", uranus=" + uranus + ", mercury=" + mercury
						+ ", mars=" + mars + ", neptune=" + neptune
						+ ", earth=" + earth + "]";
			}


			//**************************************************equals******************************************************

			@Override
			public boolean equals(Object obj) {
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Solarystem other = (Solarystem) obj;
				if (saturn == null) {
					if (other.saturn != null)
						return false;
				} else if (!saturn.equals(other.saturn))
					return false;
				if (venus == null) {
					if (other.venus != null)
						return false;
				} else if (!venus.equals(other.venus))
					return false;
				if (uranus == null) {
					if (other.uranus != null)
						return false;
				} else if (!uranus.equals(other.uranus))
					return false;
				if (mercury == null) {
					if (other.mercury != null)
						return false;
				} else if (!mercury.equals(other.mercury))
					return false;
				if (mars == null) {
					if (other.mars != null)
						return false;
				} else if (!mars.equals(other.mars))
					return false;
				if (neptune == null) {
					if (other.neptune != null)
						return false;
				} else if (!neptune.equals(other.neptune))
					return false;
				if (earth == null) {
					if (other.earth != null)
						return false;
				} else if (!earth.equals(other.earth))
					return false;
				return true;
			}

			/**
			 *
			 * @author EasyJson By xinjun
			 *
			 */
			@JSONClass("saturn")
			public static class Saturn implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 9169878943547914632L;
				/**
				 * 
				 */
				@JSONField("tree")
				private ArrayList<Tree> tree;
				/**
				 * 
				 */
				@JSONField("land")
				private ArrayList<Land> land;
				/**
				 * 
				 */
				@JSONField("animal")
				private ArrayList<Animal> animal;


				//**********************************************Getter and Setter************************************************

				public ArrayList<Tree> getTree(){
					return this.tree;
				}
				public void setTree(ArrayList<Tree> tree){
					this.tree=tree;
				}
				public ArrayList<Land> getLand(){
					return this.land;
				}
				public void setLand(ArrayList<Land> land){
					this.land=land;
				}
				public ArrayList<Animal> getAnimal(){
					return this.animal;
				}
				public void setAnimal(ArrayList<Animal> animal){
					this.animal=animal;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Saturn [tree=" + tree + ", land=" + land
							+ ", animal=" + animal + "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Saturn other = (Saturn) obj;
					if (tree == null) {
						if (other.tree != null && other.tree.size()!=0)
							return false;
					} else {
						for(int i=0;i<tree.size();i++)
							if (!tree.get(i).equals(other.tree.get(i)))
								return false;
					}
					if (land == null) {
						if (other.land != null && other.land.size()!=0)
							return false;
					} else {
						for(int i=0;i<land.size();i++)
							if (!land.get(i).equals(other.land.get(i)))
								return false;
					}
					if (animal == null) {
						if (other.animal != null && other.animal.size()!=0)
							return false;
					} else {
						for(int i=0;i<animal.size();i++)
							if (!animal.get(i).equals(other.animal.get(i)))
								return false;
					}
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("tree")
				public static class Tree implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 9113694488410850502L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int height;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getHeight(){
						return this.height;
					}
					public void setHeight(int height){
						this.height=height;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Tree [englishName=" + englishName + ", name=" + name
								+ ", height=" + height + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Tree other = (Tree) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (height != other.height)
							return false;
						return true;
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("land")
				public static class Land implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 9100474618382410182L;
					/**
					 * 
					 */
					@JSONField("provinces")
					private ArrayList<Province> provinces;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("short_name")
					private String shortName;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public ArrayList<Province> getProvinces(){
						return this.provinces;
					}
					public void setProvinces(ArrayList<Province> provinces){
						this.provinces=provinces;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public String getShortName(){
						return this.shortName;
					}
					public void setShortName(String shortName){
						this.shortName=shortName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Land [englishName=" + englishName + ", provinces=" + provinces
								+ ", name=" + name + ", shortName=" + shortName
								+ "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Land other = (Land) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (provinces == null) {
							if (other.provinces != null && other.provinces.size()!=0)
								return false;
						} else {
							for(int i=0;i<provinces.size();i++)
								if (!provinces.get(i).equals(other.provinces.get(i)))
									return false;
						}
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (shortName == null) {
							if (other.shortName != null)
								return false;
						} else if (!shortName.equals(other.shortName))
							return false;
						return true;
					}

					/**
					 *
					 * @author EasyJson By xinjun
					 *
					 */
					@JSONClass("provinces")
					public static class Province implements Serializable{

						/**
						 * 自动生成的序列化串号
						 */
						private static final long serialVersionUID = 9098822135165726054L;
						/**
						 * 
						 */
						@JSONField("peaple_count")
						private int peapleCount;
						/**
						 * 
						 */
						@JSONField("specialty")
						private ArrayList<Specialty> specialty;
						/**
						 * 
						 */
						@JSONField("area_size")
						private float areaSize;
						/**
						 * 
						 */
						@JSONField("area_code")
						private int areaCode;
						/**
						 * 
						 */
						private String name;
						/**
						 * 
						 */
						@JSONField("county")
						private County county;


						//**********************************************Getter and Setter************************************************

						public ArrayList<Specialty> getSpecialty(){
							return this.specialty;
						}
						public void setSpecialty(ArrayList<Specialty> specialty){
							this.specialty=specialty;
						}
						public int getAreaCode(){
							return this.areaCode;
						}
						public void setAreaCode(int areaCode){
							this.areaCode=areaCode;
						}
						public String getName(){
							return this.name;
						}
						public void setName(String name){
							this.name=name;
						}
						public County getCounty(){
							return this.county;
						}
						public void setCounty(County county){
							this.county=county;
						}
						public int getPeapleCount(){
							return this.peapleCount;
						}
						public void setPeapleCount(int peapleCount){
							this.peapleCount=peapleCount;
						}
						public float getAreaSize(){
							return this.areaSize;
						}
						public void setAreaSize(float areaSize){
							this.areaSize=areaSize;
						}


						//**************************************************toString******************************************************

						@Override
						public String toString() {
							return "Province [specialty=" + specialty + ", areaCode=" + areaCode
									+ ", name=" + name + ", county=" + county
									+ ", peapleCount=" + peapleCount + ", areaSize=" + areaSize
									+ "]";
						}


						//**************************************************equals******************************************************

						@Override
						public boolean equals(Object obj) {
							if (obj == null)
								return false;
							if (getClass() != obj.getClass())
								return false;
							Province other = (Province) obj;
							if (specialty == null) {
								if (other.specialty != null && other.specialty.size()!=0)
									return false;
							} else {
								for(int i=0;i<specialty.size();i++)
									if (!specialty.get(i).equals(other.specialty.get(i)))
										return false;
							}
							if (areaCode != other.areaCode)
								return false;
							if (name == null) {
								if (other.name != null)
									return false;
							} else if (!name.equals(other.name))
								return false;
							if (county == null) {
								if (other.county != null)
									return false;
							} else if (!county.equals(other.county))
								return false;
							if (peapleCount != other.peapleCount)
								return false;
							if (areaSize != other.areaSize)
								return false;
							return true;
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("specialty")
						public static class Specialty implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 9098822135165726054L;
							/**
							 * 
							 */
							private int level;
							/**
							 * 
							 */
							private String name;
							/**
							 * 
							 */
							private String from;


							//**********************************************Getter and Setter************************************************

							public int getLevel(){
								return this.level;
							}
							public void setLevel(int level){
								this.level=level;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}
							public String getFrom(){
								return this.from;
							}
							public void setFrom(String from){
								this.from=from;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "Specialty [level=" + level + ", name=" + name
										+ ", from=" + from + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								Specialty other = (Specialty) obj;
								if (level != other.level)
									return false;
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								if (from == null) {
									if (other.from != null)
										return false;
								} else if (!from.equals(other.from))
									return false;
								return true;
							}
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("county")
						public static class County implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 9088907231570653990L;
							/**
							 * 
							 */
							private float size;
							/**
							 * 
							 */
							private ArrayList<String> town;
							/**
							 * 
							 */
							private String name;


							//**********************************************Getter and Setter************************************************

							public float getSize(){
								return this.size;
							}
							public void setSize(float size){
								this.size=size;
							}
							public ArrayList<String> getTown(){
								return this.town;
							}
							public void setTown(ArrayList<String> town){
								this.town=town;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "County [size=" + size + ", town=" + town
										+ ", name=" + name + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								County other = (County) obj;
								if (size != other.size)
									return false;
								if (town == null) {
									if (other.town != null && other.town.size()!=0)
										return false;
								} else {
									for(int i=0;i<town.size();i++)
										if (!town.get(i).equals(other.town.get(i)))
											return false;
								}
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								return true;
							}
						}
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("animal")
				public static class Animal implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 9128566845950942247L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int age;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getAge(){
						return this.age;
					}
					public void setAge(int age){
						this.age=age;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Animal [englishName=" + englishName + ", name=" + name
								+ ", age=" + age + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Animal other = (Animal) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (age != other.age)
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
			@JSONClass("venus")
			public static class Venu implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 9120304425572554311L;
				/**
				 * 
				 */
				@JSONField("tree")
				private ArrayList<Tree> tree;
				/**
				 * 
				 */
				@JSONField("land")
				private ArrayList<Land> land;
				/**
				 * 
				 */
				@JSONField("animal")
				private ArrayList<Animal> animal;


				//**********************************************Getter and Setter************************************************

				public ArrayList<Tree> getTree(){
					return this.tree;
				}
				public void setTree(ArrayList<Tree> tree){
					this.tree=tree;
				}
				public ArrayList<Land> getLand(){
					return this.land;
				}
				public void setLand(ArrayList<Land> land){
					this.land=land;
				}
				public ArrayList<Animal> getAnimal(){
					return this.animal;
				}
				public void setAnimal(ArrayList<Animal> animal){
					this.animal=animal;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Venu [tree=" + tree + ", land=" + land
							+ ", animal=" + animal + "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Venu other = (Venu) obj;
					if (tree == null) {
						if (other.tree != null && other.tree.size()!=0)
							return false;
					} else {
						for(int i=0;i<tree.size();i++)
							if (!tree.get(i).equals(other.tree.get(i)))
								return false;
					}
					if (land == null) {
						if (other.land != null && other.land.size()!=0)
							return false;
					} else {
						for(int i=0;i<land.size();i++)
							if (!land.get(i).equals(other.land.get(i)))
								return false;
					}
					if (animal == null) {
						if (other.animal != null && other.animal.size()!=0)
							return false;
					} else {
						for(int i=0;i<animal.size();i++)
							if (!animal.get(i).equals(other.animal.get(i)))
								return false;
					}
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("tree")
				public static class Tree implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7775182494486187237L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int height;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getHeight(){
						return this.height;
					}
					public void setHeight(int height){
						this.height=height;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Tree [englishName=" + englishName + ", name=" + name
								+ ", height=" + height + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Tree other = (Tree) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (height != other.height)
							return false;
						return true;
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("land")
				public static class Land implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7798317272404666918L;
					/**
					 * 
					 */
					@JSONField("provinces")
					private ArrayList<Province> provinces;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("short_name")
					private String shortName;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public ArrayList<Province> getProvinces(){
						return this.provinces;
					}
					public void setProvinces(ArrayList<Province> provinces){
						this.provinces=provinces;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public String getShortName(){
						return this.shortName;
					}
					public void setShortName(String shortName){
						this.shortName=shortName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Land [englishName=" + englishName + ", provinces=" + provinces
								+ ", name=" + name + ", shortName=" + shortName
								+ "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Land other = (Land) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (provinces == null) {
							if (other.provinces != null && other.provinces.size()!=0)
								return false;
						} else {
							for(int i=0;i<provinces.size();i++)
								if (!provinces.get(i).equals(other.provinces.get(i)))
									return false;
						}
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (shortName == null) {
							if (other.shortName != null)
								return false;
						} else if (!shortName.equals(other.shortName))
							return false;
						return true;
					}

					/**
					 *
					 * @author EasyJson By xinjun
					 *
					 */
					@JSONClass("provinces")
					public static class Province implements Serializable{

						/**
						 * 自动生成的序列化串号
						 */
						private static final long serialVersionUID = 7796664789187982790L;
						/**
						 * 
						 */
						@JSONField("peaple_count")
						private int peapleCount;
						/**
						 * 
						 */
						@JSONField("specialty")
						private ArrayList<Specialty> specialty;
						/**
						 * 
						 */
						@JSONField("area_size")
						private float areaSize;
						/**
						 * 
						 */
						@JSONField("area_code")
						private int areaCode;
						/**
						 * 
						 */
						private String name;
						/**
						 * 
						 */
						@JSONField("county")
						private County county;


						//**********************************************Getter and Setter************************************************

						public ArrayList<Specialty> getSpecialty(){
							return this.specialty;
						}
						public void setSpecialty(ArrayList<Specialty> specialty){
							this.specialty=specialty;
						}
						public int getAreaCode(){
							return this.areaCode;
						}
						public void setAreaCode(int areaCode){
							this.areaCode=areaCode;
						}
						public String getName(){
							return this.name;
						}
						public void setName(String name){
							this.name=name;
						}
						public County getCounty(){
							return this.county;
						}
						public void setCounty(County county){
							this.county=county;
						}
						public int getPeapleCount(){
							return this.peapleCount;
						}
						public void setPeapleCount(int peapleCount){
							this.peapleCount=peapleCount;
						}
						public float getAreaSize(){
							return this.areaSize;
						}
						public void setAreaSize(float areaSize){
							this.areaSize=areaSize;
						}


						//**************************************************toString******************************************************

						@Override
						public String toString() {
							return "Province [specialty=" + specialty + ", areaCode=" + areaCode
									+ ", name=" + name + ", county=" + county
									+ ", peapleCount=" + peapleCount + ", areaSize=" + areaSize
									+ "]";
						}


						//**************************************************equals******************************************************

						@Override
						public boolean equals(Object obj) {
							if (obj == null)
								return false;
							if (getClass() != obj.getClass())
								return false;
							Province other = (Province) obj;
							if (specialty == null) {
								if (other.specialty != null && other.specialty.size()!=0)
									return false;
							} else {
								for(int i=0;i<specialty.size();i++)
									if (!specialty.get(i).equals(other.specialty.get(i)))
										return false;
							}
							if (areaCode != other.areaCode)
								return false;
							if (name == null) {
								if (other.name != null)
									return false;
							} else if (!name.equals(other.name))
								return false;
							if (county == null) {
								if (other.county != null)
									return false;
							} else if (!county.equals(other.county))
								return false;
							if (peapleCount != other.peapleCount)
								return false;
							if (areaSize != other.areaSize)
								return false;
							return true;
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("specialty")
						public static class Specialty implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7796664789187982790L;
							/**
							 * 
							 */
							private int level;
							/**
							 * 
							 */
							private String name;
							/**
							 * 
							 */
							private String from;


							//**********************************************Getter and Setter************************************************

							public int getLevel(){
								return this.level;
							}
							public void setLevel(int level){
								this.level=level;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}
							public String getFrom(){
								return this.from;
							}
							public void setFrom(String from){
								this.from=from;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "Specialty [level=" + level + ", name=" + name
										+ ", from=" + from + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								Specialty other = (Specialty) obj;
								if (level != other.level)
									return false;
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								if (from == null) {
									if (other.from != null)
										return false;
								} else if (!from.equals(other.from))
									return false;
								return true;
							}
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("county")
						public static class County implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7733870396889214852L;
							/**
							 * 
							 */
							private float size;
							/**
							 * 
							 */
							private ArrayList<String> town;
							/**
							 * 
							 */
							private String name;


							//**********************************************Getter and Setter************************************************

							public float getSize(){
								return this.size;
							}
							public void setSize(float size){
								this.size=size;
							}
							public ArrayList<String> getTown(){
								return this.town;
							}
							public void setTown(ArrayList<String> town){
								this.town=town;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "County [size=" + size + ", town=" + town
										+ ", name=" + name + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								County other = (County) obj;
								if (size != other.size)
									return false;
								if (town == null) {
									if (other.town != null && other.town.size()!=0)
										return false;
								} else {
									for(int i=0;i<town.size();i++)
										if (!town.get(i).equals(other.town.get(i)))
											return false;
								}
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								return true;
							}
						}
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("animal")
				public static class Animal implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7715693077210722148L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int age;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getAge(){
						return this.age;
					}
					public void setAge(int age){
						this.age=age;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Animal [englishName=" + englishName + ", name=" + name
								+ ", age=" + age + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Animal other = (Animal) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (age != other.age)
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
			@JSONClass("uranus")
			public static class Uranu implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 7765267595186082469L;
				/**
				 * 
				 */
				@JSONField("tree")
				private ArrayList<Tree> tree;
				/**
				 * 
				 */
				@JSONField("land")
				private ArrayList<Land> land;
				/**
				 * 
				 */
				@JSONField("animal")
				private ArrayList<Animal> animal;


				//**********************************************Getter and Setter************************************************

				public ArrayList<Tree> getTree(){
					return this.tree;
				}
				public void setTree(ArrayList<Tree> tree){
					this.tree=tree;
				}
				public ArrayList<Land> getLand(){
					return this.land;
				}
				public void setLand(ArrayList<Land> land){
					this.land=land;
				}
				public ArrayList<Animal> getAnimal(){
					return this.animal;
				}
				public void setAnimal(ArrayList<Animal> animal){
					this.animal=animal;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Uranu [tree=" + tree + ", land=" + land
							+ ", animal=" + animal + "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Uranu other = (Uranu) obj;
					if (tree == null) {
						if (other.tree != null && other.tree.size()!=0)
							return false;
					} else {
						for(int i=0;i<tree.size();i++)
							if (!tree.get(i).equals(other.tree.get(i)))
								return false;
					}
					if (land == null) {
						if (other.land != null && other.land.size()!=0)
							return false;
					} else {
						for(int i=0;i<land.size();i++)
							if (!land.get(i).equals(other.land.get(i)))
								return false;
					}
					if (animal == null) {
						if (other.animal != null && other.animal.size()!=0)
							return false;
					} else {
						for(int i=0;i<animal.size();i++)
							if (!animal.get(i).equals(other.animal.get(i)))
								return false;
					}
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("tree")
				public static class Tree implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7758657658024378661L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int height;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getHeight(){
						return this.height;
					}
					public void setHeight(int height){
						this.height=height;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Tree [englishName=" + englishName + ", name=" + name
								+ ", height=" + height + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Tree other = (Tree) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (height != other.height)
							return false;
						return true;
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("land")
				public static class Land implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7750395233351023429L;
					/**
					 * 
					 */
					@JSONField("provinces")
					private ArrayList<Province> provinces;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("short_name")
					private String shortName;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public ArrayList<Province> getProvinces(){
						return this.provinces;
					}
					public void setProvinces(ArrayList<Province> provinces){
						this.provinces=provinces;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public String getShortName(){
						return this.shortName;
					}
					public void setShortName(String shortName){
						this.shortName=shortName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Land [englishName=" + englishName + ", provinces=" + provinces
								+ ", name=" + name + ", shortName=" + shortName
								+ "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Land other = (Land) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (provinces == null) {
							if (other.provinces != null && other.provinces.size()!=0)
								return false;
						} else {
							for(int i=0;i<provinces.size();i++)
								if (!provinces.get(i).equals(other.provinces.get(i)))
									return false;
						}
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (shortName == null) {
							if (other.shortName != null)
								return false;
						} else if (!shortName.equals(other.shortName))
							return false;
						return true;
					}

					/**
					 *
					 * @author EasyJson By xinjun
					 *
					 */
					@JSONClass("provinces")
					public static class Province implements Serializable{

						/**
						 * 自动生成的序列化串号
						 */
						private static final long serialVersionUID = 7742132817267602789L;
						/**
						 * 
						 */
						@JSONField("peaple_count")
						private int peapleCount;
						/**
						 * 
						 */
						@JSONField("specialty")
						private ArrayList<Specialty> specialty;
						/**
						 * 
						 */
						@JSONField("area_size")
						private float areaSize;
						/**
						 * 
						 */
						@JSONField("area_code")
						private int areaCode;
						/**
						 * 
						 */
						private String name;
						/**
						 * 
						 */
						@JSONField("county")
						private County county;


						//**********************************************Getter and Setter************************************************

						public ArrayList<Specialty> getSpecialty(){
							return this.specialty;
						}
						public void setSpecialty(ArrayList<Specialty> specialty){
							this.specialty=specialty;
						}
						public int getAreaCode(){
							return this.areaCode;
						}
						public void setAreaCode(int areaCode){
							this.areaCode=areaCode;
						}
						public String getName(){
							return this.name;
						}
						public void setName(String name){
							this.name=name;
						}
						public County getCounty(){
							return this.county;
						}
						public void setCounty(County county){
							this.county=county;
						}
						public int getPeapleCount(){
							return this.peapleCount;
						}
						public void setPeapleCount(int peapleCount){
							this.peapleCount=peapleCount;
						}
						public float getAreaSize(){
							return this.areaSize;
						}
						public void setAreaSize(float areaSize){
							this.areaSize=areaSize;
						}


						//**************************************************toString******************************************************

						@Override
						public String toString() {
							return "Province [specialty=" + specialty + ", areaCode=" + areaCode
									+ ", name=" + name + ", county=" + county
									+ ", peapleCount=" + peapleCount + ", areaSize=" + areaSize
									+ "]";
						}


						//**************************************************equals******************************************************

						@Override
						public boolean equals(Object obj) {
							if (obj == null)
								return false;
							if (getClass() != obj.getClass())
								return false;
							Province other = (Province) obj;
							if (specialty == null) {
								if (other.specialty != null && other.specialty.size()!=0)
									return false;
							} else {
								for(int i=0;i<specialty.size();i++)
									if (!specialty.get(i).equals(other.specialty.get(i)))
										return false;
							}
							if (areaCode != other.areaCode)
								return false;
							if (name == null) {
								if (other.name != null)
									return false;
							} else if (!name.equals(other.name))
								return false;
							if (county == null) {
								if (other.county != null)
									return false;
							} else if (!county.equals(other.county))
								return false;
							if (peapleCount != other.peapleCount)
								return false;
							if (areaSize != other.areaSize)
								return false;
							return true;
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("specialty")
						public static class Specialty implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7742132817267602789L;
							/**
							 * 
							 */
							private int level;
							/**
							 * 
							 */
							private String name;
							/**
							 * 
							 */
							private String from;


							//**********************************************Getter and Setter************************************************

							public int getLevel(){
								return this.level;
							}
							public void setLevel(int level){
								this.level=level;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}
							public String getFrom(){
								return this.from;
							}
							public void setFrom(String from){
								this.from=from;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "Specialty [level=" + level + ", name=" + name
										+ ", from=" + from + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								Specialty other = (Specialty) obj;
								if (level != other.level)
									return false;
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								if (from == null) {
									if (other.from != null)
										return false;
								} else if (!from.equals(other.from))
									return false;
								return true;
							}
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("county")
						public static class County implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7745437783700971045L;
							/**
							 * 
							 */
							private float size;
							/**
							 * 
							 */
							private ArrayList<String> town;
							/**
							 * 
							 */
							private String name;


							//**********************************************Getter and Setter************************************************

							public float getSize(){
								return this.size;
							}
							public void setSize(float size){
								this.size=size;
							}
							public ArrayList<String> getTown(){
								return this.town;
							}
							public void setTown(ArrayList<String> town){
								this.town=town;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "County [size=" + size + ", town=" + town
										+ ", name=" + name + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								County other = (County) obj;
								if (size != other.size)
									return false;
								if (town == null) {
									if (other.town != null && other.town.size()!=0)
										return false;
								} else {
									for(int i=0;i<town.size();i++)
										if (!town.get(i).equals(other.town.get(i)))
											return false;
								}
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								return true;
							}
						}
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("animal")
				public static class Animal implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7676033458535466595L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int age;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getAge(){
						return this.age;
					}
					public void setAge(int age){
						this.age=age;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Animal [englishName=" + englishName + ", name=" + name
								+ ", age=" + age + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Animal other = (Animal) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (age != other.age)
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
			@JSONClass("mercury")
			public static class Mercury implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 7664466071723710403L;
				/**
				 * 
				 */
				@JSONField("tree")
				private ArrayList<Tree> tree;
				/**
				 * 
				 */
				@JSONField("land")
				private ArrayList<Land> land;
				/**
				 * 
				 */
				@JSONField("animal")
				private ArrayList<Animal> animal;


				//**********************************************Getter and Setter************************************************

				public ArrayList<Tree> getTree(){
					return this.tree;
				}
				public void setTree(ArrayList<Tree> tree){
					this.tree=tree;
				}
				public ArrayList<Land> getLand(){
					return this.land;
				}
				public void setLand(ArrayList<Land> land){
					this.land=land;
				}
				public ArrayList<Animal> getAnimal(){
					return this.animal;
				}
				public void setAnimal(ArrayList<Animal> animal){
					this.animal=animal;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Mercury [tree=" + tree + ", land=" + land
							+ ", animal=" + animal + "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Mercury other = (Mercury) obj;
					if (tree == null) {
						if (other.tree != null && other.tree.size()!=0)
							return false;
					} else {
						for(int i=0;i<tree.size();i++)
							if (!tree.get(i).equals(other.tree.get(i)))
								return false;
					}
					if (land == null) {
						if (other.land != null && other.land.size()!=0)
							return false;
					} else {
						for(int i=0;i<land.size();i++)
							if (!land.get(i).equals(other.land.get(i)))
								return false;
					}
					if (animal == null) {
						if (other.animal != null && other.animal.size()!=0)
							return false;
					} else {
						for(int i=0;i<animal.size();i++)
							if (!animal.get(i).equals(other.animal.get(i)))
								return false;
					}
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("tree")
				public static class Tree implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7689253328563906915L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int height;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getHeight(){
						return this.height;
					}
					public void setHeight(int height){
						this.height=height;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Tree [englishName=" + englishName + ", name=" + name
								+ ", height=" + height + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Tree other = (Tree) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (height != other.height)
							return false;
						return true;
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("land")
				public static class Land implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7614891553748350081L;
					/**
					 * 
					 */
					@JSONField("provinces")
					private ArrayList<Province> provinces;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("short_name")
					private String shortName;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public ArrayList<Province> getProvinces(){
						return this.provinces;
					}
					public void setProvinces(ArrayList<Province> provinces){
						this.provinces=provinces;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public String getShortName(){
						return this.shortName;
					}
					public void setShortName(String shortName){
						this.shortName=shortName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Land [englishName=" + englishName + ", provinces=" + provinces
								+ ", name=" + name + ", shortName=" + shortName
								+ "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Land other = (Land) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (provinces == null) {
							if (other.provinces != null && other.provinces.size()!=0)
								return false;
						} else {
							for(int i=0;i<provinces.size();i++)
								if (!provinces.get(i).equals(other.provinces.get(i)))
									return false;
						}
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (shortName == null) {
							if (other.shortName != null)
								return false;
						} else if (!shortName.equals(other.shortName))
							return false;
						return true;
					}

					/**
					 *
					 * @author EasyJson By xinjun
					 *
					 */
					@JSONClass("provinces")
					public static class Province implements Serializable{

						/**
						 * 自动生成的序列化串号
						 */
						private static final long serialVersionUID = 7619849003398402466L;
						/**
						 * 
						 */
						@JSONField("peaple_count")
						private int peapleCount;
						/**
						 * 
						 */
						@JSONField("specialty")
						private ArrayList<Specialty> specialty;
						/**
						 * 
						 */
						@JSONField("area_size")
						private float areaSize;
						/**
						 * 
						 */
						@JSONField("area_code")
						private int areaCode;
						/**
						 * 
						 */
						private String name;
						/**
						 * 
						 */
						@JSONField("county")
						private County county;


						//**********************************************Getter and Setter************************************************

						public ArrayList<Specialty> getSpecialty(){
							return this.specialty;
						}
						public void setSpecialty(ArrayList<Specialty> specialty){
							this.specialty=specialty;
						}
						public int getAreaCode(){
							return this.areaCode;
						}
						public void setAreaCode(int areaCode){
							this.areaCode=areaCode;
						}
						public String getName(){
							return this.name;
						}
						public void setName(String name){
							this.name=name;
						}
						public County getCounty(){
							return this.county;
						}
						public void setCounty(County county){
							this.county=county;
						}
						public int getPeapleCount(){
							return this.peapleCount;
						}
						public void setPeapleCount(int peapleCount){
							this.peapleCount=peapleCount;
						}
						public float getAreaSize(){
							return this.areaSize;
						}
						public void setAreaSize(float areaSize){
							this.areaSize=areaSize;
						}


						//**************************************************toString******************************************************

						@Override
						public String toString() {
							return "Province [specialty=" + specialty + ", areaCode=" + areaCode
									+ ", name=" + name + ", county=" + county
									+ ", peapleCount=" + peapleCount + ", areaSize=" + areaSize
									+ "]";
						}


						//**************************************************equals******************************************************

						@Override
						public boolean equals(Object obj) {
							if (obj == null)
								return false;
							if (getClass() != obj.getClass())
								return false;
							Province other = (Province) obj;
							if (specialty == null) {
								if (other.specialty != null && other.specialty.size()!=0)
									return false;
							} else {
								for(int i=0;i<specialty.size();i++)
									if (!specialty.get(i).equals(other.specialty.get(i)))
										return false;
							}
							if (areaCode != other.areaCode)
								return false;
							if (name == null) {
								if (other.name != null)
									return false;
							} else if (!name.equals(other.name))
								return false;
							if (county == null) {
								if (other.county != null)
									return false;
							} else if (!county.equals(other.county))
								return false;
							if (peapleCount != other.peapleCount)
								return false;
							if (areaSize != other.areaSize)
								return false;
							return true;
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("specialty")
						public static class Specialty implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7619849003398402466L;
							/**
							 * 
							 */
							private int level;
							/**
							 * 
							 */
							private String name;
							/**
							 * 
							 */
							private String from;


							//**********************************************Getter and Setter************************************************

							public int getLevel(){
								return this.level;
							}
							public void setLevel(int level){
								this.level=level;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}
							public String getFrom(){
								return this.from;
							}
							public void setFrom(String from){
								this.from=from;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "Specialty [level=" + level + ", name=" + name
										+ ", from=" + from + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								Specialty other = (Specialty) obj;
								if (level != other.level)
									return false;
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								if (from == null) {
									if (other.from != null)
										return false;
								} else if (!from.equals(other.from))
									return false;
								return true;
							}
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("county")
						public static class County implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7609934099803330401L;
							/**
							 * 
							 */
							private float size;
							/**
							 * 
							 */
							private ArrayList<String> town;
							/**
							 * 
							 */
							private String name;


							//**********************************************Getter and Setter************************************************

							public float getSize(){
								return this.size;
							}
							public void setSize(float size){
								this.size=size;
							}
							public ArrayList<String> getTown(){
								return this.town;
							}
							public void setTown(ArrayList<String> town){
								this.town=town;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "County [size=" + size + ", town=" + town
										+ ", name=" + name + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								County other = (County) obj;
								if (size != other.size)
									return false;
								if (town == null) {
									if (other.town != null && other.town.size()!=0)
										return false;
								} else {
									for(int i=0;i<town.size();i++)
										if (!town.get(i).equals(other.town.get(i)))
											return false;
								}
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								return true;
							}
						}
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("animal")
				public static class Animal implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7638026327371862466L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int age;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getAge(){
						return this.age;
					}
					public void setAge(int age){
						this.age=age;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Animal [englishName=" + englishName + ", name=" + name
								+ ", age=" + age + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Animal other = (Animal) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (age != other.age)
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
			@JSONClass("mars")
			public static class Mar implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 7988352923927720267L;
				/**
				 * 
				 */
				@JSONField("tree")
				private ArrayList<Tree> tree;
				/**
				 * 
				 */
				@JSONField("land")
				private ArrayList<Land> land;
				/**
				 * 
				 */
				@JSONField("animal")
				private ArrayList<Animal> animal;


				//**********************************************Getter and Setter************************************************

				public ArrayList<Tree> getTree(){
					return this.tree;
				}
				public void setTree(ArrayList<Tree> tree){
					this.tree=tree;
				}
				public ArrayList<Land> getLand(){
					return this.land;
				}
				public void setLand(ArrayList<Land> land){
					this.land=land;
				}
				public ArrayList<Animal> getAnimal(){
					return this.animal;
				}
				public void setAnimal(ArrayList<Animal> animal){
					this.animal=animal;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Mar [tree=" + tree + ", land=" + land
							+ ", animal=" + animal + "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Mar other = (Mar) obj;
					if (tree == null) {
						if (other.tree != null && other.tree.size()!=0)
							return false;
					} else {
						for(int i=0;i<tree.size();i++)
							if (!tree.get(i).equals(other.tree.get(i)))
								return false;
					}
					if (land == null) {
						if (other.land != null && other.land.size()!=0)
							return false;
					} else {
						for(int i=0;i<land.size();i++)
							if (!land.get(i).equals(other.land.get(i)))
								return false;
					}
					if (animal == null) {
						if (other.animal != null && other.animal.size()!=0)
							return false;
					} else {
						for(int i=0;i<animal.size();i++)
							if (!animal.get(i).equals(other.animal.get(i)))
								return false;
					}
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("tree")
				public static class Tree implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 8019750117929620587L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int height;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getHeight(){
						return this.height;
					}
					public void setHeight(int height){
						this.height=height;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Tree [englishName=" + englishName + ", name=" + name
								+ ", height=" + height + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Tree other = (Tree) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (height != other.height)
							return false;
						return true;
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("land")
				public static class Land implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 8004877764684496139L;
					/**
					 * 
					 */
					@JSONField("provinces")
					private ArrayList<Province> provinces;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("short_name")
					private String shortName;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public ArrayList<Province> getProvinces(){
						return this.provinces;
					}
					public void setProvinces(ArrayList<Province> provinces){
						this.provinces=provinces;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public String getShortName(){
						return this.shortName;
					}
					public void setShortName(String shortName){
						this.shortName=shortName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Land [englishName=" + englishName + ", provinces=" + provinces
								+ ", name=" + name + ", shortName=" + shortName
								+ "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Land other = (Land) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (provinces == null) {
							if (other.provinces != null && other.provinces.size()!=0)
								return false;
						} else {
							for(int i=0;i<provinces.size();i++)
								if (!provinces.get(i).equals(other.provinces.get(i)))
									return false;
						}
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (shortName == null) {
							if (other.shortName != null)
								return false;
						} else if (!shortName.equals(other.shortName))
							return false;
						return true;
					}

					/**
					 *
					 * @author EasyJson By xinjun
					 *
					 */
					@JSONClass("provinces")
					public static class Province implements Serializable{

						/**
						 * 自动生成的序列化串号
						 */
						private static final long serialVersionUID = 8009835214334548523L;
						/**
						 * 
						 */
						@JSONField("peaple_count")
						private int peapleCount;
						/**
						 * 
						 */
						@JSONField("specialty")
						private ArrayList<Specialty> specialty;
						/**
						 * 
						 */
						@JSONField("area_size")
						private float areaSize;
						/**
						 * 
						 */
						@JSONField("area_code")
						private int areaCode;
						/**
						 * 
						 */
						private String name;
						/**
						 * 
						 */
						@JSONField("county")
						private County county;


						//**********************************************Getter and Setter************************************************

						public ArrayList<Specialty> getSpecialty(){
							return this.specialty;
						}
						public void setSpecialty(ArrayList<Specialty> specialty){
							this.specialty=specialty;
						}
						public int getAreaCode(){
							return this.areaCode;
						}
						public void setAreaCode(int areaCode){
							this.areaCode=areaCode;
						}
						public String getName(){
							return this.name;
						}
						public void setName(String name){
							this.name=name;
						}
						public County getCounty(){
							return this.county;
						}
						public void setCounty(County county){
							this.county=county;
						}
						public int getPeapleCount(){
							return this.peapleCount;
						}
						public void setPeapleCount(int peapleCount){
							this.peapleCount=peapleCount;
						}
						public float getAreaSize(){
							return this.areaSize;
						}
						public void setAreaSize(float areaSize){
							this.areaSize=areaSize;
						}


						//**************************************************toString******************************************************

						@Override
						public String toString() {
							return "Province [specialty=" + specialty + ", areaCode=" + areaCode
									+ ", name=" + name + ", county=" + county
									+ ", peapleCount=" + peapleCount + ", areaSize=" + areaSize
									+ "]";
						}


						//**************************************************equals******************************************************

						@Override
						public boolean equals(Object obj) {
							if (obj == null)
								return false;
							if (getClass() != obj.getClass())
								return false;
							Province other = (Province) obj;
							if (specialty == null) {
								if (other.specialty != null && other.specialty.size()!=0)
									return false;
							} else {
								for(int i=0;i<specialty.size();i++)
									if (!specialty.get(i).equals(other.specialty.get(i)))
										return false;
							}
							if (areaCode != other.areaCode)
								return false;
							if (name == null) {
								if (other.name != null)
									return false;
							} else if (!name.equals(other.name))
								return false;
							if (county == null) {
								if (other.county != null)
									return false;
							} else if (!county.equals(other.county))
								return false;
							if (peapleCount != other.peapleCount)
								return false;
							if (areaSize != other.areaSize)
								return false;
							return true;
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("specialty")
						public static class Specialty implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 8009835214334548523L;
							/**
							 * 
							 */
							private int level;
							/**
							 * 
							 */
							private String name;
							/**
							 * 
							 */
							private String from;


							//**********************************************Getter and Setter************************************************

							public int getLevel(){
								return this.level;
							}
							public void setLevel(int level){
								this.level=level;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}
							public String getFrom(){
								return this.from;
							}
							public void setFrom(String from){
								this.from=from;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "Specialty [level=" + level + ", name=" + name
										+ ", from=" + from + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								Specialty other = (Specialty) obj;
								if (level != other.level)
									return false;
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								if (from == null) {
									if (other.from != null)
										return false;
								} else if (!from.equals(other.from))
									return false;
								return true;
							}
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("county")
						public static class County implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7947040826330747882L;
							/**
							 * 
							 */
							private float size;
							/**
							 * 
							 */
							private ArrayList<String> town;
							/**
							 * 
							 */
							private String name;


							//**********************************************Getter and Setter************************************************

							public float getSize(){
								return this.size;
							}
							public void setSize(float size){
								this.size=size;
							}
							public ArrayList<String> getTown(){
								return this.town;
							}
							public void setTown(ArrayList<String> town){
								this.town=town;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "County [size=" + size + ", town=" + town
										+ ", name=" + name + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								County other = (County) obj;
								if (size != other.size)
									return false;
								if (town == null) {
									if (other.town != null && other.town.size()!=0)
										return false;
								} else {
									for(int i=0;i<town.size();i++)
										if (!town.get(i).equals(other.town.get(i)))
											return false;
								}
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								return true;
							}
						}
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("animal")
				public static class Animal implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7937125922735675817L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int age;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getAge(){
						return this.age;
					}
					public void setAge(int age){
						this.age=age;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Animal [englishName=" + englishName + ", name=" + name
								+ ", age=" + age + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Animal other = (Animal) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (age != other.age)
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
			@JSONClass("neptune")
			public static class Neptune implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 7928863502357287881L;
				/**
				 * 
				 */
				@JSONField("tree")
				private ArrayList<Tree> tree;
				/**
				 * 
				 */
				@JSONField("land")
				private ArrayList<Land> land;
				/**
				 * 
				 */
				@JSONField("animal")
				private ArrayList<Animal> animal;


				//**********************************************Getter and Setter************************************************

				public ArrayList<Tree> getTree(){
					return this.tree;
				}
				public void setTree(ArrayList<Tree> tree){
					this.tree=tree;
				}
				public ArrayList<Land> getLand(){
					return this.land;
				}
				public void setLand(ArrayList<Land> land){
					this.land=land;
				}
				public ArrayList<Animal> getAnimal(){
					return this.animal;
				}
				public void setAnimal(ArrayList<Animal> animal){
					this.animal=animal;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Neptune [tree=" + tree + ", land=" + land
							+ ", animal=" + animal + "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Neptune other = (Neptune) obj;
					if (tree == null) {
						if (other.tree != null && other.tree.size()!=0)
							return false;
					} else {
						for(int i=0;i<tree.size();i++)
							if (!tree.get(i).equals(other.tree.get(i)))
								return false;
					}
					if (land == null) {
						if (other.land != null && other.land.size()!=0)
							return false;
					} else {
						for(int i=0;i<land.size();i++)
							if (!land.get(i).equals(other.land.get(i)))
								return false;
					}
					if (animal == null) {
						if (other.animal != null && other.animal.size()!=0)
							return false;
					} else {
						for(int i=0;i<animal.size();i++)
							if (!animal.get(i).equals(other.animal.get(i)))
								return false;
					}
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("tree")
				public static class Tree implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7973480570682595818L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int height;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getHeight(){
						return this.height;
					}
					public void setHeight(int height){
						this.height=height;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Tree [englishName=" + englishName + ", name=" + name
								+ ", height=" + height + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Tree other = (Tree) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (height != other.height)
							return false;
						return true;
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("land")
				public static class Land implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7966870629225924714L;
					/**
					 * 
					 */
					@JSONField("provinces")
					private ArrayList<Province> provinces;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("short_name")
					private String shortName;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public ArrayList<Province> getProvinces(){
						return this.provinces;
					}
					public void setProvinces(ArrayList<Province> provinces){
						this.provinces=provinces;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public String getShortName(){
						return this.shortName;
					}
					public void setShortName(String shortName){
						this.shortName=shortName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Land [englishName=" + englishName + ", provinces=" + provinces
								+ ", name=" + name + ", shortName=" + shortName
								+ "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Land other = (Land) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (provinces == null) {
							if (other.provinces != null && other.provinces.size()!=0)
								return false;
						} else {
							for(int i=0;i<provinces.size();i++)
								if (!provinces.get(i).equals(other.provinces.get(i)))
									return false;
						}
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (shortName == null) {
							if (other.shortName != null)
								return false;
						} else if (!shortName.equals(other.shortName))
							return false;
						return true;
					}

					/**
					 *
					 * @author EasyJson By xinjun
					 *
					 */
					@JSONClass("provinces")
					public static class Province implements Serializable{

						/**
						 * 自动生成的序列化串号
						 */
						private static final long serialVersionUID = 7965218146009240586L;
						/**
						 * 
						 */
						@JSONField("peaple_count")
						private int peapleCount;
						/**
						 * 
						 */
						@JSONField("specialty")
						private ArrayList<Specialty> specialty;
						/**
						 * 
						 */
						@JSONField("area_size")
						private float areaSize;
						/**
						 * 
						 */
						@JSONField("area_code")
						private int areaCode;
						/**
						 * 
						 */
						private String name;
						/**
						 * 
						 */
						@JSONField("county")
						private County county;


						//**********************************************Getter and Setter************************************************

						public ArrayList<Specialty> getSpecialty(){
							return this.specialty;
						}
						public void setSpecialty(ArrayList<Specialty> specialty){
							this.specialty=specialty;
						}
						public int getAreaCode(){
							return this.areaCode;
						}
						public void setAreaCode(int areaCode){
							this.areaCode=areaCode;
						}
						public String getName(){
							return this.name;
						}
						public void setName(String name){
							this.name=name;
						}
						public County getCounty(){
							return this.county;
						}
						public void setCounty(County county){
							this.county=county;
						}
						public int getPeapleCount(){
							return this.peapleCount;
						}
						public void setPeapleCount(int peapleCount){
							this.peapleCount=peapleCount;
						}
						public float getAreaSize(){
							return this.areaSize;
						}
						public void setAreaSize(float areaSize){
							this.areaSize=areaSize;
						}


						//**************************************************toString******************************************************

						@Override
						public String toString() {
							return "Province [specialty=" + specialty + ", areaCode=" + areaCode
									+ ", name=" + name + ", county=" + county
									+ ", peapleCount=" + peapleCount + ", areaSize=" + areaSize
									+ "]";
						}


						//**************************************************equals******************************************************

						@Override
						public boolean equals(Object obj) {
							if (obj == null)
								return false;
							if (getClass() != obj.getClass())
								return false;
							Province other = (Province) obj;
							if (specialty == null) {
								if (other.specialty != null && other.specialty.size()!=0)
									return false;
							} else {
								for(int i=0;i<specialty.size();i++)
									if (!specialty.get(i).equals(other.specialty.get(i)))
										return false;
							}
							if (areaCode != other.areaCode)
								return false;
							if (name == null) {
								if (other.name != null)
									return false;
							} else if (!name.equals(other.name))
								return false;
							if (county == null) {
								if (other.county != null)
									return false;
							} else if (!county.equals(other.county))
								return false;
							if (peapleCount != other.peapleCount)
								return false;
							if (areaSize != other.areaSize)
								return false;
							return true;
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("specialty")
						public static class Specialty implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7965218146009240586L;
							/**
							 * 
							 */
							private int level;
							/**
							 * 
							 */
							private String name;
							/**
							 * 
							 */
							private String from;


							//**********************************************Getter and Setter************************************************

							public int getLevel(){
								return this.level;
							}
							public void setLevel(int level){
								this.level=level;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}
							public String getFrom(){
								return this.from;
							}
							public void setFrom(String from){
								this.from=from;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "Specialty [level=" + level + ", name=" + name
										+ ", from=" + from + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								Specialty other = (Specialty) obj;
								if (level != other.level)
									return false;
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								if (from == null) {
									if (other.from != null)
										return false;
								} else if (!from.equals(other.from))
									return false;
								return true;
							}
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("county")
						public static class County implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7968523116737576138L;
							/**
							 * 
							 */
							private float size;
							/**
							 * 
							 */
							private ArrayList<String> town;
							/**
							 * 
							 */
							private String name;


							//**********************************************Getter and Setter************************************************

							public float getSize(){
								return this.size;
							}
							public void setSize(float size){
								this.size=size;
							}
							public ArrayList<String> getTown(){
								return this.town;
							}
							public void setTown(ArrayList<String> town){
								this.town=town;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "County [size=" + size + ", town=" + town
										+ ", name=" + name + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								County other = (County) obj;
								if (size != other.size)
									return false;
								if (town == null) {
									if (other.town != null && other.town.size()!=0)
										return false;
								} else {
									for(int i=0;i<town.size();i++)
										if (!town.get(i).equals(other.town.get(i)))
											return false;
								}
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								return true;
							}
						}
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("animal")
				public static class Animal implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7953650759197484394L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int age;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getAge(){
						return this.age;
					}
					public void setAge(int age){
						this.age=age;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Animal [englishName=" + englishName + ", name=" + name
								+ ", age=" + age + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Animal other = (Animal) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (age != other.age)
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
			@JSONClass("earth")
			public static class Earth implements Serializable{

				/**
				 * 自动生成的序列化串号
				 */
				private static final long serialVersionUID = 7879288984381927560L;
				/**
				 * 
				 */
				@JSONField("tree")
				private ArrayList<Tree> tree;
				/**
				 * 
				 */
				@JSONField("land")
				private ArrayList<Land> land;
				/**
				 * 
				 */
				@JSONField("animal")
				private ArrayList<Animal> animal;


				//**********************************************Getter and Setter************************************************

				public ArrayList<Tree> getTree(){
					return this.tree;
				}
				public void setTree(ArrayList<Tree> tree){
					this.tree=tree;
				}
				public ArrayList<Land> getLand(){
					return this.land;
				}
				public void setLand(ArrayList<Land> land){
					this.land=land;
				}
				public ArrayList<Animal> getAnimal(){
					return this.animal;
				}
				public void setAnimal(ArrayList<Animal> animal){
					this.animal=animal;
				}


				//**************************************************toString******************************************************

				@Override
				public String toString() {
					return "Earth [tree=" + tree + ", land=" + land
							+ ", animal=" + animal + "]";
				}


				//**************************************************equals******************************************************

				@Override
				public boolean equals(Object obj) {
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Earth other = (Earth) obj;
					if (tree == null) {
						if (other.tree != null && other.tree.size()!=0)
							return false;
					} else {
						for(int i=0;i<tree.size();i++)
							if (!tree.get(i).equals(other.tree.get(i)))
								return false;
					}
					if (land == null) {
						if (other.land != null && other.land.size()!=0)
							return false;
					} else {
						for(int i=0;i<land.size();i++)
							if (!land.get(i).equals(other.land.get(i)))
								return false;
					}
					if (animal == null) {
						if (other.animal != null && other.animal.size()!=0)
							return false;
					} else {
						for(int i=0;i<animal.size();i++)
							if (!animal.get(i).equals(other.animal.get(i)))
								return false;
					}
					return true;
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("tree")
				public static class Tree implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7910686178383827881L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int height;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getHeight(){
						return this.height;
					}
					public void setHeight(int height){
						this.height=height;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Tree [englishName=" + englishName + ", name=" + name
								+ ", height=" + height + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Tree other = (Tree) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (height != other.height)
							return false;
						return true;
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("land")
				public static class Land implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 7824757012461547559L;
					/**
					 * 
					 */
					@JSONField("provinces")
					private ArrayList<Province> provinces;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("short_name")
					private String shortName;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public ArrayList<Province> getProvinces(){
						return this.provinces;
					}
					public void setProvinces(ArrayList<Province> provinces){
						this.provinces=provinces;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public String getShortName(){
						return this.shortName;
					}
					public void setShortName(String shortName){
						this.shortName=shortName;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Land [englishName=" + englishName + ", provinces=" + provinces
								+ ", name=" + name + ", shortName=" + shortName
								+ "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Land other = (Land) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (provinces == null) {
							if (other.provinces != null && other.provinces.size()!=0)
								return false;
						} else {
							for(int i=0;i<provinces.size();i++)
								if (!provinces.get(i).equals(other.provinces.get(i)))
									return false;
						}
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (shortName == null) {
							if (other.shortName != null)
								return false;
						} else if (!shortName.equals(other.shortName))
							return false;
						return true;
					}

					/**
					 *
					 * @author EasyJson By xinjun
					 *
					 */
					@JSONClass("provinces")
					public static class Province implements Serializable{

						/**
						 * 自动生成的序列化串号
						 */
						private static final long serialVersionUID = 7823104529244863431L;
						/**
						 * 
						 */
						@JSONField("peaple_count")
						private int peapleCount;
						/**
						 * 
						 */
						@JSONField("specialty")
						private ArrayList<Specialty> specialty;
						/**
						 * 
						 */
						@JSONField("area_size")
						private float areaSize;
						/**
						 * 
						 */
						@JSONField("area_code")
						private int areaCode;
						/**
						 * 
						 */
						private String name;
						/**
						 * 
						 */
						@JSONField("county")
						private County county;


						//**********************************************Getter and Setter************************************************

						public ArrayList<Specialty> getSpecialty(){
							return this.specialty;
						}
						public void setSpecialty(ArrayList<Specialty> specialty){
							this.specialty=specialty;
						}
						public int getAreaCode(){
							return this.areaCode;
						}
						public void setAreaCode(int areaCode){
							this.areaCode=areaCode;
						}
						public String getName(){
							return this.name;
						}
						public void setName(String name){
							this.name=name;
						}
						public County getCounty(){
							return this.county;
						}
						public void setCounty(County county){
							this.county=county;
						}
						public int getPeapleCount(){
							return this.peapleCount;
						}
						public void setPeapleCount(int peapleCount){
							this.peapleCount=peapleCount;
						}
						public float getAreaSize(){
							return this.areaSize;
						}
						public void setAreaSize(float areaSize){
							this.areaSize=areaSize;
						}


						//**************************************************toString******************************************************

						@Override
						public String toString() {
							return "Province [specialty=" + specialty + ", areaCode=" + areaCode
									+ ", name=" + name + ", county=" + county
									+ ", peapleCount=" + peapleCount + ", areaSize=" + areaSize
									+ "]";
						}


						//**************************************************equals******************************************************

						@Override
						public boolean equals(Object obj) {
							if (obj == null)
								return false;
							if (getClass() != obj.getClass())
								return false;
							Province other = (Province) obj;
							if (specialty == null) {
								if (other.specialty != null && other.specialty.size()!=0)
									return false;
							} else {
								for(int i=0;i<specialty.size();i++)
									if (!specialty.get(i).equals(other.specialty.get(i)))
										return false;
							}
							if (areaCode != other.areaCode)
								return false;
							if (name == null) {
								if (other.name != null)
									return false;
							} else if (!name.equals(other.name))
								return false;
							if (county == null) {
								if (other.county != null)
									return false;
							} else if (!county.equals(other.county))
								return false;
							if (peapleCount != other.peapleCount)
								return false;
							if (areaSize != other.areaSize)
								return false;
							return true;
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("specialty")
						public static class Specialty implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7867721593275204072L;
							/**
							 * 
							 */
							private int level;
							/**
							 * 
							 */
							private String name;
							/**
							 * 
							 */
							private String from;


							//**********************************************Getter and Setter************************************************

							public int getLevel(){
								return this.level;
							}
							public void setLevel(int level){
								this.level=level;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}
							public String getFrom(){
								return this.from;
							}
							public void setFrom(String from){
								this.from=from;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "Specialty [level=" + level + ", name=" + name
										+ ", from=" + from + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								Specialty other = (Specialty) obj;
								if (level != other.level)
									return false;
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								if (from == null) {
									if (other.from != null)
										return false;
								} else if (!from.equals(other.from))
									return false;
								return true;
							}
						}

						/**
						 *
						 * @author EasyJson By xinjun
						 *
						 */
						@JSONClass("county")
						public static class County implements Serializable{

							/**
							 * 自动生成的序列化串号
							 */
							private static final long serialVersionUID = 7871026564003539624L;
							/**
							 * 
							 */
							private float size;
							/**
							 * 
							 */
							private ArrayList<String> town;
							/**
							 * 
							 */
							private String name;


							//**********************************************Getter and Setter************************************************

							public float getSize(){
								return this.size;
							}
							public void setSize(float size){
								this.size=size;
							}
							public ArrayList<String> getTown(){
								return this.town;
							}
							public void setTown(ArrayList<String> town){
								this.town=town;
							}
							public String getName(){
								return this.name;
							}
							public void setName(String name){
								this.name=name;
							}


							//**************************************************toString******************************************************

							@Override
							public String toString() {
								return "County [size=" + size + ", town=" + town
										+ ", name=" + name + "]";
							}


							//**************************************************equals******************************************************

							@Override
							public boolean equals(Object obj) {
								if (obj == null)
									return false;
								if (getClass() != obj.getClass())
									return false;
								County other = (County) obj;
								if (size != other.size)
									return false;
								if (town == null) {
									if (other.town != null && other.town.size()!=0)
										return false;
								} else {
									for(int i=0;i<town.size();i++)
										if (!town.get(i).equals(other.town.get(i)))
											return false;
								}
								if (name == null) {
									if (other.name != null)
										return false;
								} else if (!name.equals(other.name))
									return false;
								return true;
							}
						}
					}
				}

				/**
				 *
				 * @author EasyJson By xinjun
				 *
				 */
				@JSONClass("animal")
				public static class Animal implements Serializable{

					/**
					 * 自动生成的序列化串号
					 */
					private static final long serialVersionUID = 8213090740181009488L;
					/**
					 * 
					 */
					private String name;
					/**
					 * 
					 */
					@JSONField("english_name")
					private String englishName;
					/**
					 * 
					 */
					private int age;


					//**********************************************Getter and Setter************************************************

					public String getEnglishName(){
						return this.englishName;
					}
					public void setEnglishName(String englishName){
						this.englishName=englishName;
					}
					public String getName(){
						return this.name;
					}
					public void setName(String name){
						this.name=name;
					}
					public int getAge(){
						return this.age;
					}
					public void setAge(int age){
						this.age=age;
					}


					//**************************************************toString******************************************************

					@Override
					public String toString() {
						return "Animal [englishName=" + englishName + ", name=" + name
								+ ", age=" + age + "]";
					}


					//**************************************************equals******************************************************

					@Override
					public boolean equals(Object obj) {
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						Animal other = (Animal) obj;
						if (englishName == null) {
							if (other.englishName != null)
								return false;
						} else if (!englishName.equals(other.englishName))
							return false;
						if (name == null) {
							if (other.name != null)
								return false;
						} else if (!name.equals(other.name))
							return false;
						if (age != other.age)
							return false;
						return true;
					}
				}
			}
		}
	}
}
