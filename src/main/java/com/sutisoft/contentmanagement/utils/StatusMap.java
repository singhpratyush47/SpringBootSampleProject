package com.sutisoft.contentmanagement.utils;

public interface StatusMap {


	public static final Integer ACTIVE =1;
	public static final Integer INACTIVE =2;
	public static final Integer DRAFT =3;
	public static final Integer DELETE =4;
	public static final Integer NEW =5;
	public static final Integer INPROGRESS =6;
	public static final Integer HOLD =7;
	public static final Integer CANCELLED =8;
	public static final Integer AUTOCLOSED =9;
	public static final Integer DELETED =10;
	public static final Integer CLOSED =11;


	public static final Integer MARKASUNREAD =12;
	public static final Integer MAILREAD =13;
	public static final Integer MARKUNREAD =14;
	public static final Integer SENTMAIL =15;
	public static final Integer DRAFTMAIL =16;
	public static final Integer JUNKMAIL =17;
	public static final Integer FORWARD =18;
	public static final Integer PERMANENTDELETE =19;
	public static final Integer MARKREAD =20;
	public static final Integer REPLY =21;
	public static final Integer FLAG =22;
	public static final Integer MARKFILTEREDINBOX =23;
	public static final Integer SPAM =24;
	
	public static final Integer REOPENED =25;
	
	public static final Integer INFOREQUESTED =26;
	public static final Integer CANNOTRESOLVE =27;
	public static final Integer DUPLICATE =28;
	public static final Integer ASSIGNED =29;
	public static final Integer NOTWORKEDYET =30;
	public static final Integer NEEDTOVERIFY =33;
	
	//Linking Tickets Related Statuses
	public static final Integer Addresses =1;
	public static final Integer Is_Addressed_By =2;
	public static final Integer Is_Parent_Of =3;
	public static final Integer Is_Child_Of =4;
	public static final Integer Is_Related_To =5;

}
