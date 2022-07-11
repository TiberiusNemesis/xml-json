for $book in doc("../xml/books.xml")/library/publisher/book
return (
    <author>
        {data($book/author)}
    </author>
)