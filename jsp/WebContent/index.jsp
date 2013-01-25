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
				<kendo:treeView name="employees" dataTextField="name" select="employeeSelected" >
					<kendo:dataSource>
						<kendo:dataSource-transport>
							<kendo:dataSource-transport-read url="api/employees">
							</kendo:dataSource-transport-read>
						</kendo:dataSource-transport>
						<kendo:dataSource-schema data="data">
							<kendo:dataSource-schema-hierarchical-model id="id" 
							hasChildren="hasChildren"></kendo:dataSource-schema-hierarchical-model>
						</kendo:dataSource-schema> 
					</kendo:dataSource>
				</kendo:treeView>
			</div>
			<div class="span8">
				<h2>Address Information</h2>
				<kendo:grid name="address_information" autoBind="false" editable="true">
					<kendo:dataSource>
						<kendo:dataSource-transport read="api/employee/addresses">
							<kendo:dataSource-transport-parameterMap>
								<script>
									function parameterMap(options, operation) {
										switch (operation) {
											case "read":
												return { employeeId: selectedEmployeeId };
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
						<kendo:grid-column field="addressLine1" title="Street" />
						<kendo:grid-column field="city" title="City" />
						<kendo:grid-column field="stateProvinceID" title="State" />
						<kendo:grid-column field="postalCode" title="Zip Code" />
					</kendo:grid-columns>
					<kendo:grid-toolbar>
						<kendo:grid-toolbarItem name="create"/>
						<kendo:grid-toolbarItem name="save"/>
						<kendo:grid-toolbarItem name="cancel"/>
					</kendo:grid-toolbar>
				</kendo:grid>
				<h2>Pay History</h2>
				<kendo:grid name="pay_history" autoBind="false">
					<kendo:dataSource>
						<kendo:dataSource-transport read="api/employee/payhistory">
							<kendo:dataSource-transport-destroy url="api/payhistory" type="DELETE"></kendo:dataSource-transport-destroy>
							<kendo:dataSource-transport-create url="api/payhistory" type="PUT"></kendo:dataSource-transport-create>
							<kendo:dataSource-transport-update url="api/payhistory" type="POST"></kendo:dataSource-transport-update>
							<kendo:dataSource-transport-parameterMap>
								<script>
									function parameterMap(options, operation) {
										switch (operation) {
											case "read":
												return { employeeId: selectedEmployeeId };
												break;
											case "create":
												options.EmployeeID = selectedEmployeeId;
												return options;
												break;
											default:
												return options;
										};
									}
								</script>
							</kendo:dataSource-transport-parameterMap>
						</kendo:dataSource-transport>
						<kendo:dataSource-schema>
							<kendo:dataSource-schema-parse>
								<script>
									function parse(data) {
										return $.map(data, function(item) {
											item.rateChangeDate = kendo.parseDate(item.rateChangeDate);
											return item;
										});
									}
								</script>
							</kendo:dataSource-schema-parse>
							<kendo:dataSource-schema-model>
								<kendo:dataSource-schema-model-fields>
									<kendo:dataSource-schema-model-field name="rateChangeDate" type="date" />
									<kendo:dataSource-schema-model-field name="rate" type="number">
										<kendo:dataSource-schema-model-field-validation required="true" min="10"/>
									</kendo:dataSource-schema-model-field>
									<kendo:dataSource-schema-model-field name="payFrequency" type="number" defaultValue="1">
										<kendo:dataSource-schema-model-field-validation required="true" min="1" max="4"/>
									</kendo:dataSource-schema-model-field>
								</kendo:dataSource-schema-model-fields>
							</kendo:dataSource-schema-model>
						</kendo:dataSource-schema>
					</kendo:dataSource>
					<kendo:grid-columns>
						<kendo:grid-column field="rate" title="Rate (Hourly)" format="{0:c}" width="150px"></kendo:grid-column>
						<kendo:grid-column field="rateChangeDate" title="Date Of Last Change" format="{0:D}" ></kendo:grid-column>
						<kendo:grid-column field="payFrequency" title="Frequency (Weeks)"  width="150px"></kendo:grid-column>
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
			
			selectedEmployeeId = this.dataItem(e.node).id;
			
			// get a reference to the grid
			$("#pay_history").data("kendoGrid").dataSource.read();
			$("#address_information").data("kendoGrid").dataSource.read();
		};
	
	</script>

</body>
</html>