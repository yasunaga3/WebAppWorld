package model;

public enum Continent {

	Asia("Asia"), Europe("Europe"), NorthAmerica("North America"), Africa("Africa"), Oceania("Oceania"), Antarctica("Antarctica"), SouthAmerica("South America");
    // フィールドを定義
    private String name;

	private Continent(String name) {
		this.name = name;
	}

    // メソッド
    public String getValue() {
        return this.name;
    }
}
