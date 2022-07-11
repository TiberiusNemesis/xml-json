for $place in doc("../xml/notifyActivation.xml")//Place[@xsi:type="pla:NBNLocation"]
return (
    <ID>
        {data($place/ID)}
    </ID>
)