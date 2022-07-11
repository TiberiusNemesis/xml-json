declare variable $param as xs:string external;
for $x in doc("../xml/books.xml")/library/publisher/book
where $x/author = $param
return (<book><title>{data($x/title)}</title>, <author>{data($x/author)}</author></book>)
