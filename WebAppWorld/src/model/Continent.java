package model;

public enum Continent {

	ASIA("Asia"),
	AFRICA("Africa"),
	EUROPE("Europe"),
	NORTH_AMERICA("North America"),
	SOUTH_AMERICA("South America"),
	OCEANIA("Oceania"),
	ANTARCTICA("Antarctica");

    // フィールドの定義
    private String name;

    // コンストラクタの定義
    private Continent(String name) {
        this.name = name;
    }

    // メソッド
    public String getValue() {
        return this.name;
    }
}
