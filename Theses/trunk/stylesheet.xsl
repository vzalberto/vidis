<?xml version="1.0"?>
<!DOCTYPE xsl:stylesheet [
<!ENTITY importdb SYSTEM "importdb-fo.etd">
]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/TR/xhtml1/transitional" xmlns:xi="http://www.w3.org/2001/XInclude" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0" exclude-result-prefixes="#default">
&importdb; 

<!-- dbdoclet stuff -->
<!--  <xsl:import href ="http://docbook.sourceforge.net/release/xsl/current/fo/docbook.xsl"/> -->

  <!-- <xsl:include href="doclet-xslt\fo\dbdoclet-titlepage.xsl"/>-->
<!--  <xsl:include href="doclet-xslt\functions.xsl"/>
  <xsl:include href="doclet-xslt\fo\fop1.xsl"/>
  <xsl:include href="doclet-xslt\fo\themes\color.xsl"/>
  <xsl:include href="doclet-xslt\fo\synopsis.xsl"/> -->

  <!-- SECTION Parameter -->
  <xsl:param name="admon.graphics">1</xsl:param>
  <xsl:param name="admon.graphics.extension">.svg</xsl:param>
  <xsl:param name="admon.graphics.path">doclet/docbook/xsl/images/</xsl:param>
  <xsl:param name="callout.graphics">1</xsl:param>
  <xsl:param name="callout.graphics.extension">.gif</xsl:param>
  <xsl:param name="callout.graphics.path">doclet/docbook/xsl/images/callouts/</xsl:param>
  <xsl:param name="autotoc.label.separator"> </xsl:param>
  <xsl:param name="body.font.family">CMU Serif, serif, Symbol, ZapfDingbats</xsl:param>
  <xsl:param name="title.font.family">CMU Sans Serif, sanf-serif, Symbol, ZapfDingbats</xsl:param>
  <xsl:param name="monospace.font.family">CMU Typewriter Text, monospace, Symbol, ZapfDingbats</xsl:param>
  <xsl:param name="chapter.autolabel">1</xsl:param>
  <xsl:param name="column.count.back">1</xsl:param>
  <xsl:param name="column.count.body">1</xsl:param>
  <xsl:param name="column.count.front">1</xsl:param>
  <xsl:param name="column.count.index">1</xsl:param>
  <xsl:param name="draft.mode">no</xsl:param>
  <xsl:param name="draft.watermark.image">doclet/docbook/xsl/images/draft.png</xsl:param>

  <xsl:param name="fop.extensions">0</xsl:param>
  <xsl:param name="generate.index">1</xsl:param>
<!--  <xsl:param name="insert.xref.page.number">1</xsl:param> -->
  <xsl:param name="page.orientation">portrait</xsl:param>
  <xsl:param name="shade.verbatim">1</xsl:param>
  <xsl:param name="tablecolumns.extension">1</xsl:param>
  <xsl:param name="title.margin.left">0in</xsl:param>
  <xsl:param name="toc.section.depth">1</xsl:param>
<!-- produces an error
 <xsl:param name="use.extensions">1</xsl:param>
-->
<!-- eof dbdoclet stuff -->

<xsl:param name="alignment">justify</xsl:param>
<xsl:variable name="body.font.master">11</xsl:variable>
<xsl:param name="fop1.extensions" select="1"/>
<xsl:param name="section.autolabel" select="1"/>
<xsl:param name="section.label.includes.component.label" select="1"/>
<xsl:param name="section.autolabel.max.depth" select="2"/>
<xsl:param name="xref.with.number.and.title" select="0"/>
<xsl:param name="paper.type" select="A4"/>
<xsl:param name="variablelist.as.blocks" select="1"/>
<xsl:param name="bibliography.numbered" select="1"/>
<xsl:param name="line-height" select="1.32"/>
<xsl:param name="body.start.indent" select="'0pt'"/>
<xsl:param name="body.end.indent" select="'0pt'"/>
<xsl:param name="orderedlist.label.width" select="'1.8em'"/>
<xsl:param name="header.rule" select="0"/>
<xsl:param name="footer.rule" select="0"/>

<xsl:attribute-set name="shade.verbatim.style">
  <xsl:attribute name="background-color">#F0F0F0</xsl:attribute>
  <xsl:attribute name="border">thin #E0E0E0 solid</xsl:attribute>
</xsl:attribute-set>


<!-- for duplex -->
<xsl:param name="double.sided" select="1" />

<xsl:template name="header.content">
</xsl:template>

<xsl:attribute-set name="component.title.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master * 1.4"/><xsl:text>pt</xsl:text></xsl:attribute><xsl:attribute name="space-after.optimum">0em</xsl:attribute><xsl:attribute name="space-after.minimum">0em</xsl:attribute><xsl:attribute name="space-after.maximum">0em</xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="section.title.level1.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master * 1.3"/><xsl:text>pt</xsl:text></xsl:attribute><xsl:attribute name="space-after.optimum">1em</xsl:attribute><xsl:attribute name="space-after.minimum">1em</xsl:attribute><xsl:attribute name="space-after.maximum">1em</xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="section.title.level2.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master * 1.1"/><xsl:text>pt</xsl:text></xsl:attribute><xsl:attribute name="space-after.optimum">1em</xsl:attribute><xsl:attribute name="space-after.minimum">1em</xsl:attribute><xsl:attribute name="space-after.maximum">1em</xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="section.title.level3.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt
  </xsl:attribute><xsl:attribute name="space-after.optimum">1em</xsl:attribute><xsl:attribute name="space-after.minimum">1em</xsl:attribute><xsl:attribute name="space-after.maximum">1em</xsl:attribute><xsl:attribute name="font-family"><xsl:value-of select="$body.font.family"/></xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="section.title.level4.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt
  </xsl:attribute><xsl:attribute name="space-after.optimum">0.5em</xsl:attribute><xsl:attribute name="space-after.minimum">0.5em</xsl:attribute><xsl:attribute name="space-after.maximum">0.5em</xsl:attribute><xsl:attribute name="font-family"><xsl:value-of select="$body.font.family"/></xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="formal.title.properties"><xsl:attribute name="font-size"><xsl:value-of select="$body.font.master"/>pt</xsl:attribute><xsl:attribute name="text-align">center</xsl:attribute><xsl:attribute name="font-weight">normal</xsl:attribute></xsl:attribute-set>

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


<xsl:attribute-set name="formal.object.properties">
  <xsl:attribute name="space-before.minimum">0.5em</xsl:attribute>
  <xsl:attribute name="space-before.optimum">0.5em</xsl:attribute>
  <xsl:attribute name="space-before.maximum">0.5em</xsl:attribute>
  <xsl:attribute name="space-after.minimum">0.5em</xsl:attribute>
  <xsl:attribute name="space-after.optimum">0.5em</xsl:attribute>
  <xsl:attribute name="space-after.maximum">0.5em</xsl:attribute>
  <xsl:attribute name="keep-together.within-column">always</xsl:attribute>
</xsl:attribute-set>

<xsl:attribute-set name="verbatim.properties">
  <xsl:attribute name="space-before.minimum">0.5em</xsl:attribute>
  <xsl:attribute name="space-before.optimum">0.5em</xsl:attribute>
  <xsl:attribute name="space-before.maximum">0.5em</xsl:attribute>
  <xsl:attribute name="space-after.minimum">0.5em</xsl:attribute>
  <xsl:attribute name="space-after.optimum">0.5em</xsl:attribute>
  <xsl:attribute name="space-after.maximum">0.5em</xsl:attribute>
  <xsl:attribute name="padding-left">0.5em</xsl:attribute>
  <xsl:attribute name="padding-right">0.5em</xsl:attribute>
  <xsl:attribute name="padding-top">0.5em</xsl:attribute>
  <xsl:attribute name="padding-bottom">0.4em</xsl:attribute>
  <xsl:attribute name="keep-together.within-column">always</xsl:attribute>
</xsl:attribute-set>

<xsl:attribute-set name="normal.para.spacing"><xsl:attribute name="orphans">3</xsl:attribute><xsl:attribute name="widows">3</xsl:attribute><xsl:attribute name="space-before.optimum">0.0cm</xsl:attribute><xsl:attribute name="space-before.minimum">0.0cm</xsl:attribute><xsl:attribute name="space-before.maximum">0.0cm</xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="list.block.spacing"><xsl:attribute name="space-before.optimum">0em</xsl:attribute><xsl:attribute name="space-before.minimum">0em</xsl:attribute><xsl:attribute name="space-before.maximum">0em</xsl:attribute><xsl:attribute name="space-after.optimum">0em</xsl:attribute><xsl:attribute name="space-after.minimum">0em</xsl:attribute><xsl:attribute name="space-after.maximum">0em</xsl:attribute></xsl:attribute-set>

	    <xsl:attribute-set name="list.item.spacing"><xsl:attribute name="space-before.optimum">0em</xsl:attribute><xsl:attribute name="space-before.minimum">0em</xsl:attribute><xsl:attribute name="space-before.maximum">0em</xsl:attribute></xsl:attribute-set>

<xsl:attribute-set name="biblioentry.properties"><xsl:attribute name="space-before.optimum">0em</xsl:attribute><xsl:attribute name="space-before.minimum">0em</xsl:attribute><xsl:attribute name="space-before.maximum">0em</xsl:attribute><xsl:attribute name="space-after">6pt</xsl:attribute><xsl:attribute name="orphans">3</xsl:attribute><xsl:attribute name="widows">3</xsl:attribute><xsl:attribute name="line-height">1</xsl:attribute></xsl:attribute-set>

<xsl:template match="varlistentry/term"><fo:inline font-weight="bold"><fo:inline><xsl:call-template name="simple.xlink"><xsl:with-param name="content"><xsl:apply-templates/></xsl:with-param></xsl:call-template></fo:inline><xsl:choose><xsl:when test="not(following-sibling::term)"/><!-- do nothing --><xsl:otherwise><!-- * if we have multiple terms in the same varlistentry, generate --><!-- * a separator (", " by default) and/or an additional line --><!-- * break after each one except the last --><fo:inline><xsl:value-of select="$variablelist.term.separator"/></fo:inline><xsl:if test="not($variablelist.term.break.after = '0')"><fo:block/></xsl:if></xsl:otherwise></xsl:choose></fo:inline></xsl:template>

<!--<xsl:template match="para[1]"><fo:block text-indent="0em"><xsl:apply-imports/></fo:block></xsl:template>-->

<xsl:template match="para"><xsl:choose><xsl:when test="'para' = name((preceding-sibling::*)[position() = last()])"><fo:block text-indent="1em"><xsl:apply-imports/></fo:block></xsl:when><xsl:otherwise><fo:block><xsl:apply-imports/></fo:block></xsl:otherwise></xsl:choose></xsl:template>

<xsl:template match="article/section[1]"><fo:block break-before="page"><xsl:apply-imports/></fo:block></xsl:template>
<xsl:template match="article/bibliography"><fo:block break-before="page"><xsl:apply-imports/></fo:block></xsl:template>

</xsl:stylesheet>
