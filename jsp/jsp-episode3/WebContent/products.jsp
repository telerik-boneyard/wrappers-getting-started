<%@taglib prefix="shared" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<shared:header></shared:header>

<shared:navbar></shared:navbar>

<div class="container">
	<div class="row">
		<div class="span12">
			<h3>The Grid</h3>
			<!-- create a kendo ui grid with server paging enabled -->
			<kendo:grid name="products" pageable="true">
				<kendo:grid-pageable pageSizes="true" refresh="true" buttonCount="3" />
				<kendo:dataSource pageSize="5" serverPaging="true">
					<!-- defines what url to call for data -->
					<kendo:dataSource-transport>
						<kendo:dataSource-transport-read url="api/products" />	
					</kendo:dataSource-transport>
					<kendo:dataSource-schema total="Total" data="Data"></kendo:dataSource-schema>
				</kendo:dataSource>
				<!-- grid column definitions -->
				<kendo:grid-columns>
					<kendo:grid-column title="Name" field="ProductName"></kendo:grid-column>
					<kendo:grid-column title="Supplier" field="Supplier.SupplierName"></kendo:grid-column>
					<kendo:grid-column title="Category" field="Category.CategoryName"></kendo:grid-column>
					<kendo:grid-column title="# In Stock" field="UnitsInStock"></kendo:grid-column>
					<kendo:grid-column title="Price" field="UnitPrice"></kendo:grid-column>
					<kendo:grid-column field="Discontinued"></kendo:grid-column>
				</kendo:grid-columns>
			</kendo:grid>
		</div>
	</div>
</div>

<shared:footer></shared:footer>