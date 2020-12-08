package kr.or.connect.reservesystem.enums;

public enum RenameType {
	A("성인"), Y("청소년"), B("유아"), S("셋트"), D("장애인"), C("지역주민"), E("어얼리버드"), V("VIP"), R("R석");

	private String name;

	private String getName() {
		return name;
	}

	private RenameType(String name) {
		this.name = name;
	}

	public static String lookUp(String name) {
		try {
			return RenameType.valueOf(name).getName();//valueof 없으면 null 아님 무조건 illegalArgumentException날림
		} catch (IllegalArgumentException e) {
			return name + "석";
		}
	}
}
