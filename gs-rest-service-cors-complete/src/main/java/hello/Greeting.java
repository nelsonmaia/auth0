package hello;

public class Greeting {

	private final long id;
	private final String content;
	private static String defaultContent = "Hello, ";;

	public String getContent() {
		return content;
	}

	public Greeting() {
        this.id = -1;
        this.content = "";
    }

	public Greeting(final long id, final String content, final String greeting) {
		this.id = id;
		defaultContent = greeting;
		this.content = defaultContent + content;
	}

	public Greeting(final long id, final String content) {
		this.id = id;
		this.content = defaultContent + content;
		System.out.println(this.content);
	}

	public long getId() {
		return id;
	}

	public static String getDefaultContent() {
		return defaultContent;
	}

	public static void setDefaultContent(final String defaultContent) {
		if (defaultContent == null) {
			Greeting.defaultContent = "Hello, ";
		} else {
			Greeting.defaultContent = defaultContent;
		}
	}

}
