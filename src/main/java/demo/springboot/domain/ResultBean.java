package demo.springboot.domain;

public class ResultBean {
	public int state;
	public String msg;
	public Version version;
	
	public class Version{
		public String version;
		public String url;
	}
}
