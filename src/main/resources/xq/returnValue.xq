for $x in doc("../xml/notifyActivation.xml")//ItemInvolvesResource/DescribedBy
where $x/Characteristic[ID='Rack Name' and type='Splitter Port']
return (
    <RackName>
        {data($x/value)}
    </RackName>
)