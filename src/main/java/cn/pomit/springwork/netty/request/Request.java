package cn.pomit.springwork.netty.request;

public class Request {
    /**
     * 模块号
     */
    private Integer module;

    /**
     * 命令号
     */
    private String command;

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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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
     * @param data
     * @return
     */
    public static Request getRequestDTO(Integer module, String command, String data){
        Request requestDTO = new Request();
        requestDTO.setCommand(command);
        requestDTO.setData(data);
        requestDTO.setModule(module);
        return requestDTO;
    }
}
