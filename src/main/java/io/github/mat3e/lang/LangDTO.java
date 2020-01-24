package io.github.mat3e.lang;

class LangDTO {

    private Integer id;
    private String code;
    private String emoji;

    // DTO (Data Transfer Object)
    // get Lang object through constructor and map necessary fields from it
    public LangDTO(Lang lang) {
        this.id = lang.getId();
        this.code = lang.getCode();
        this.emoji = lang.getEmoji();
    }

//    public LangDTO() {}

    /**
     * In order to have POJO (Plain Old Java Object) we need to create getters nad setters
     * Jackson object mapper require POJO
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}
