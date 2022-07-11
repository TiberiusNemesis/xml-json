declare namespace mhe="http://www.marlo.com.au/cim/common/messageHeader/v3";

for $x in doc("../xml/notifyActivation.xml")//mhe:MessageHeader
return (
<mhe:MessageHeader>
<Priority>
        {data($x/priority)}
</Priority>
<timestamp>
        {data(current-dateTime())}
</timestamp>
</mhe:MessageHeader>
)
