package example;

public interface JsonMapper {

    <T> T convertToEntity(String book, Class<T> r);
}
