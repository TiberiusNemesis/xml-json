<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:mr="http://www.marlo.com.au/training/xquery">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <mr:result>
            <mr:average_price>19.285714285714285</mr:average_price>
            <xsl:for-each select="library/publisher">
                <xsl:for-each select="book[price>19.285714285714285]">
                    <mr:expensive_book>
                        <mr:title>
                            <xsl:value-of select="title"/>
                        </mr:title>
                        <mr:current_price>
                            <xsl:value-of select="price"/>
                        </mr:current_price>
                        <mr:price_difference>
                            <xsl:value-of select="price - 19.285714285714285"/>
                        </mr:price_difference>
                    </mr:expensive_book>
                </xsl:for-each>
            </xsl:for-each>
        </mr:result>
    </xsl:template>
</xsl:stylesheet>