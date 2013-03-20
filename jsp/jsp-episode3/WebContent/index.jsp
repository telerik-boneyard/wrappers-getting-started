<%@taglib prefix="shared" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<shared:header></shared:header>

<shared:navbar></shared:navbar>

<div class="container">
	<div class="row">
		<div class="span12">
			<h3>Remote Data</h3>
			<!--  create a Kendo UI TreeView set to display the Name field from the models.Employee object -->
			<kendo:treeView name="things" dataTextField="Name">
				<!--  define the datasource -->
				<kendo:dataSource>
					<!--  set the read transport which sends a GET request to api/employees -->
					<kendo:dataSource-transport read="api/employees" />
					<!--  tell kendo ui about the structure of each employee object in the schema -->
					<kendo:dataSource-schema>
						<!--  the id field is what is sent to the server in request for child nodes. hasChildren
							  is how kendo ui knows whether or not to display an expand error next to a TreeView node -->
						<kendo:dataSource-schema-hierarchical-model id="EmployeeID" hasChildren="HasChildren " />
					</kendo:dataSource-schema>
				</kendo:dataSource>
			</kendo:treeView>
		</div>
	</div>
</div>

<shared:footer></shared:footer>
