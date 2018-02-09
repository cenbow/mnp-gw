package miw.util;

public class MnpEnv {
	public static boolean isDev() {
		 String profile =System.getProperty("spring.profiles.active");
		boolean r= "dev".equals(profile);
		return r;
	}
}
