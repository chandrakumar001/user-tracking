package example;

public class BookMapper {

    public static BookDTO mapToBookDTO(final Book book) {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setGenres(book.getGenres());
        bookDTO.setPublished(book.isPublished());
        bookDTO.setTitle(book.getTitle());
        return bookDTO;
    }
}
