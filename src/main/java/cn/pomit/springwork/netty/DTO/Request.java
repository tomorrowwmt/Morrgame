package cn.pomit.springwork.netty.DTO;

public class Request {
    /**
     * 模块号
     */
    private Integer module;

    /**
     * 命令号
     */
    private String cmd;

    /**
     * 目标
     */
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * 获得实体对象
     * @param module
     * @param cmd
     * @param data
     * @return
     */
    public static Request getRequestDTO(Integer module, String cmd, String data){
        Request requestDTO = new Request();
        requestDTO.setCmd(cmd);
        requestDTO.setData(data);
        requestDTO.setModule(module);
        return requestDTO;
    }
}
