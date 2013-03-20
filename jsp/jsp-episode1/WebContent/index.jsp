<%@taglib prefix="shared" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<shared:header></shared:header>

<% 
	// create a generic list of strings
	java.util.List<String> data = new java.util.ArrayList<String>();

	// add some strings to the list
	data.add("foo");
	data.add("bar");
	data.add("baz");
%>

<div class="container">
	<div class="row">
		<!--  create a kendo ui dropdown list -->
		<kendo:dropDownList name="things">
			<!-- set it's datasource to the generic list (data variable) -->
			<kendo:dataSource data="<%= data %>" />
		</kendo:dropDownList>
	</div>
</div>

<shared:footer></shared:footer>
