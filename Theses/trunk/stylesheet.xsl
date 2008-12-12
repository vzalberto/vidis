<?xml version="1.0"?>
<!DOCTYPE xsl:stylesheet [
<!ENTITY importdb SYSTEM "importdb-fo.etd">
]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/TR/xhtml1/transitional" xmlns:xi="http://www.w3.org/2001/XInclude" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0" exclude-result-prefixes="#default">
&importdb;

<xsl:variable name="body.font.master">11</xsl:variable>
<xsl:param name="fop1.extensions" select="1"/>
<xsl:param name="section.autolabel" select="1"/>
<xsl:param name="xref.with.number.and.title" select="0"/>
<xsl:param name="paper.type" select="A4"/>
<xsl:param name="variablelist.as.blocks" select="1"/>
<xsl:param name="bibliography.numbered" select="1"/>
<xsl:param name="line-height" select="1.8"/>
<xsl:param name="body.start.indent" select="'0pt'"/>
<xsl:param name="body.end.indent" select="'0pt'"/>
<xsl:param name="orderedlist.label.width" select="'1.8em'"/>
<xsl:template name="header.content">
</xsl:template>

<xsl:attribute-set name="section.title.level1.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt
  </xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="section.title.level2.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt
  </xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="section.title.level3.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt
  </xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="section.title.level4.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt
  </xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="formal.title.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt</xsl:attribute><xsl:attribute name="text-align">center</xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="figure.properties"><xsl:attribute name="keep-together">always</xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="bibliography.titlepage.recto.style"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt</xsl:attribute><xsl:attribute name="keep-with-next.within-column">always</xsl:attribute></xsl:attribute-set>


<xsl:param name="formal.title.placement">
figure after
example after
equation after
table after
procedure after
task after
</xsl:param>

<xsl:attribute-set name="normal.para.spacing"><xsl:attribute name="orphans">3</xsl:attribute><xsl:attribute name="widows">3</xsl:attribute><xsl:attribute name="space-before.optimum">0.1cm</xsl:attribute><xsl:attribute name="space-before.minimum">0.1cm</xsl:attribute><xsl:attribute name="space-before.maximum">0.1cm</xsl:attribute></xsl:attribute-set>



<xsl:attribute-set name="list.block.spacing"><xsl:attribute name="space-before.optimum">0em</xsl:attribute><xsl:attribute name="space-before.minimum">0em</xsl:attribute><xsl:attribute name="space-before.maximum">0em</xsl:attribute><xsl:attribute name="space-after.optimum">0em</xsl:attribute><xsl:attribute name="space-after.minimum">0em</xsl:attribute><xsl:attribute name="space-after.maximum">0em</xsl:attribute></xsl:attribute-set>

	    <xsl:attribute-set name="list.item.spacing"><xsl:attribute name="space-before.optimum">0em</xsl:attribute><xsl:attribute name="space-before.minimum">0em</xsl:attribute><xsl:attribute name="space-before.maximum">0em</xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="biblioentry.properties"><xsl:attribute name="space-before.optimum">0em</xsl:attribute><xsl:attribute name="space-before.minimum">0em</xsl:attribute><xsl:attribute name="space-before.maximum">0em</xsl:attribute><xsl:attribute name="space-after">6pt</xsl:attribute><xsl:attribute name="orphans">3</xsl:attribute><xsl:attribute name="widows">3</xsl:attribute><xsl:attribute name="line-height">1</xsl:attribute></xsl:attribute-set>

<xsl:template match="varlistentry/term"><fo:inline font-weight="bold"><fo:inline><xsl:call-template name="simple.xlink"><xsl:with-param name="content"><xsl:apply-templates/></xsl:with-param></xsl:call-template></fo:inline><xsl:choose><xsl:when test="not(following-sibling::term)"/><!-- do nothing --><xsl:otherwise><!-- * if we have multiple terms in the same varlistentry, generate --><!-- * a separator (", " by default) and/or an additional line --><!-- * break after each one except the last --><fo:inline><xsl:value-of select="$variablelist.term.separator"/></fo:inline><xsl:if test="not($variablelist.term.break.after = '0')"><fo:block/></xsl:if></xsl:otherwise></xsl:choose></fo:inline></xsl:template>

<!--<xsl:template match="para[1]"><fo:block text-indent="0em"><xsl:apply-imports/></fo:block></xsl:template>-->

<xsl:template match="para"><xsl:choose><xsl:when test="'para' = name((preceding-sibling::*)[position() = last()])"><fo:block text-indent="1em"><xsl:apply-imports/></fo:block></xsl:when><xsl:otherwise><fo:block text-indent="0em"><xsl:apply-imports/></fo:block></xsl:otherwise></xsl:choose></xsl:template>

<xsl:template match="article/section[1]"><fo:block break-before="page"><xsl:apply-imports/></fo:block></xsl:template>
<xsl:template match="article/bibliography"><fo:block break-before="page"><xsl:apply-imports/></fo:block></xsl:template>

</xsl:stylesheet>
