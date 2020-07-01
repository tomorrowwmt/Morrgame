package cn.pomit.springwork.netty.DTO;
/**
 * 回复消息
 *
 */
public class Response {
	
	/**
	 * 模块号
	 */
	private Integer module;
	
	/**
	 * 命令号
	 */
	private String cmd;
	
	/**
	 * 结果码
	 */
	private int stateCode = ResultCode.SUCCESS;
	
	/**
	 * 数据
	 */
	private String data;
	
	public Response() {
	}
	
	public Response(Request message) {
		this.module = message.getModule();
		this.cmd = message.getCmd();
	}
	
	public Response(Integer module, String cmd, String data){
		this.module = module;
		this.cmd = cmd;
		this.data=data;
	}

	public Integer getModule() {
		return module;
	}

	public void setModule(Integer module) {
		this.module = module;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
