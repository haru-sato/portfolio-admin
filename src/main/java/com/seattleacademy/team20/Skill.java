package com.seattleacademy.team20;

public class Skill {
//	public int id;
	public String category;
	public String name;
	public int score;

//	public Skill(String string, String string2, int int2) {
//    // TODO 自動生成されたコンストラクター・スタブ
//  }

  public  Skill(String category, String name, int score) {
//		このSkillはskillCotrollerのl58のSkillと一緒
		// TODO 自動生成されたコンストラクター・スタブ
//		this.id = id;
		this.category = category;
		this.name = name;
		this.score = score;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


}
