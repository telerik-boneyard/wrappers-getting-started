<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="shared" tagdir="/WEB-INF/tags"%>

<shared:header></shared:header>

<body>

	<shared:menu></shared:menu>

	<div class="container">
		<div class="row">
			<div class="span12">
				<h2>TreeView</h2>
				<h3>Employees</h3>
				<kendo:treeView name="employees" dragAndDrop="true" dataTextField="FullName" >
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
		</div>
	</div>

</body>
</html>