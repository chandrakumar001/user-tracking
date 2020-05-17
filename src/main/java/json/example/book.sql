CREATE TABLE test.books (
  book_id serial NOT NULL,
  data jsonb
);
INSERT INTO test.books VALUES (1, '{"title": "Sleeping Beauties", "genres": ["Fiction", "Thriller", "Horror"], "published": false}');
INSERT INTO test.books VALUES (2, '{"title": "Influence", "genres": ["Marketing & Sales", "Self-Help ", "Psychology"], "published": true}');
INSERT INTO test.books VALUES (3, '{"title": "The Dictator''s Handbook", "genres": ["Law", "Politics"], "authors": ["Bruce Bueno de Mesquita", "Alastair Smith"], "published": true}');
INSERT INTO test.books VALUES (4, '{"title": "Deep Work", "genres": ["Productivity", "Reference"], "published": true}');
INSERT INTO test.books VALUES (5, '{"title": "Siddhartha", "genres": ["Fiction", "Spirituality"], "published": true}');

SELECT data->'title' AS title FROM test.books;
SELECT * FROM test.books WHERE data->'published' = 'false';
