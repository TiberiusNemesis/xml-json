# XML & JSON

## XML

XML (or Extensible Markup Language) is a W3C-recommended markup language whose main purpose is to store arbitrary data. It has a very loose set of rules, which allows for highly diverse content which can be used by many other applications.

## XPath & XQuery

XPath and XQuery are querying languages used to select XML nodes or node-sets. 
XQueries can be executed in Java by using the Saxon S9 API. 

## XSLT

Extensible Stylesheet Language for Transformation (or XSLT for short) is a transformation language. I.E, it generates another file that may use the contents of an XML file. This transformation may result in an HTML, PDF, PNG, or even another XML file.

## JAXB

Jakarta XML Binding (or JAXB, formally Java Architecture for XML Binding) allows us to marshal Java classes (i.e. map Java classes to XML files) and unmarshal XML files (i.e. transform an XML file into a Java object).

By using the JAXB2 Maven Plugin, we can automatically model marshal-ready Java classes by providing valid XML schemas.

## JSON

Javascript Object Notation, commonly known as JSON, is a lightweight data-interchange file format used very frequently (as of 2022) for web services and data exchange. Using minimal syntax, it stores values inside key-value pairs, making it easy to read, easy to parse and easy to write (even manually).

JSON files can be queried and parsed by using JQ. Examples:

#### Finding all book titles from books.json:

```bash
cat books.json| jq -c '[.publisher[].books[].title]'
```

Result:

```
["The Green Mile",
"The Catcher in the Rye",
"I, Robot",
"Foundation Novels",
"Pygmalion",
"Dune",
"Dune Messiah"]
```

#### Finding all unique IDs from 'transitions' in transitions.json:

```bash
cat transitions.json|jq -c '[.transitions[].id] | unique'
```

Result:

```
["11",
"21",
"31",
"41",
"51",
"61"]
```




