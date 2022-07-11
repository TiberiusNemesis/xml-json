<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:mr="http://www.marlo.com.au/training/xquery">
    <xsl:output method="xml" indent="yes"/>
    <xsl:param name="external"/>
    <xsl:template match="/">
        <mr:author_list>
            <xsl:for-each select="library/publisher">
                <xsl:for-each select="book[author=$external]">
                    <mr:author>
                        <mr:name>
                            <xsl:value-of select="$external"/>
                        </mr:name>
                        <mr:title>
                            <xsl:value-of select="title"/>
                        </mr:title>
                    </mr:author>
                </xsl:for-each>
            </xsl:for-each>
        </mr:author_list>
    </xsl:template>
</xsl:stylesheet>