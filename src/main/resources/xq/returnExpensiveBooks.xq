for $book in doc("../xml/books.xml")/library/publisher/book
where $book/price>19.28
return (<expensive_book>
            <title>
                {data($book/title)}
            </title>
        <current_price>
            {data($book/price)}
        </current_price>
        <price_difference>
            {data($book/price) - 19.285714285714285}
        </price_difference>
    </expensive_book>
)