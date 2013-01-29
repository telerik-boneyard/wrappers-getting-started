<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>JSP: Getting Started</title>
<link href="css/kendo.common.min.css" rel="stylesheet">
<link href="css/kendo.bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/kendo.all.min.js"></script>
</head>
<style>
	#details {
		display: none;
	}
</style>
<body>

	<div class="navbar navbar-static-top">
		<div class="navbar-inner">
			<div class="container">
				<span class="brand">
					<a href="#">HR Dashboard</a>
				</span>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="span4">
				<h2>Employees</h2>
				<kendo:treeView name="employees" dragAndDrop="true" dataTextField="FullName" select="employeeSelected" >
					<kendo:dataSource>
						<kendo:dataSource-transport>
							<kendo:dataSource-transport-read url="api/employees">
							</kendo:dataSource-transport-read>
						</kendo:dataSource-transport>
						<kendo:dataSource-schema>
							<kendo:dataSource-schema-hierarchical-model id="EmployeeID" 
							hasChildren="HasChildren"></kendo:dataSource-schema-hierarchical-model>
						</kendo:dataSource-schema> 
					</kendo:dataSource>
				</kendo:treeView>
			</div>
			<div class="span8" id="details">
				<h2 id="name"></h2>
				<h3>Territories</h3>
				<kendo:grid name="employee_territories" autoBind="false" editable="true">
					<kendo:dataSource>
						<kendo:dataSource-transport read="api/employees/territories">
							<kendo:dataSource-transport-parameterMap>
								<script>
									function parameterMap(options, operation) {
										switch (operation) {
											case "read":
												return { EmployeeID: selectedEmployeeId };
												break;
											default:
												return options;
										}
									}
								</script>
							</kendo:dataSource-transport-parameterMap>
						</kendo:dataSource-transport>
					</kendo:dataSource>
					<kendo:grid-columns>
						<kendo:grid-column field="RegionDescription" title="Region" />
						<kendo:grid-column field="TerritoryDescription" title="Territory" />
					</kendo:grid-columns>
					<kendo:grid-toolbar>
						<kendo:grid-toolbarItem name="create"/>
						<kendo:grid-toolbarItem name="save"/>
						<kendo:grid-toolbarItem name="cancel"/>
					</kendo:grid-toolbar>
				</kendo:grid>
			</div>
		</div>
	</div> 

	<script>
	
		var selectedEmployeeId = 1;
	
		// TODO: should these methods be wrapped in an iffe? Is that
		// an unncessary layer of complication?
		var employeeSelected = function(e) {
			
			var selectedEmployee = this.dataItem(e.node);
			
			selectedEmployeeId = selectedEmployee.id;
			
			$("#name").html(selectedEmployee.name);
			
			// get a reference to the grid
			$("#employee_territories").data("kendoGrid").dataSource.read();
			
			$("#details").show();
		};
	
	</script>

</body>
</html>