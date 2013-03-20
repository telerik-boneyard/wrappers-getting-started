<%@taglib prefix="shared" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@page import="models.*"%>

<shared:header></shared:header>

<shared:navbar></shared:navbar>

<div class="container">
	<div class="row">
		<div class="span12">
			<h3>The Grid</h3>
			<!-- create a kendo ui grid with server paging enabled -->
			 <kendo:grid name="products" pageable="true">
			 	<kendo:grid-editable mode="popup" confirmation="Are you sure you want to delete this product?"/>
				<kendo:grid-pageable pageSizes="true" refresh="true" buttonCount="3"></kendo:grid-pageable>
				<kendo:grid-toolbar>
					<kendo:grid-toolbarItem name="create"/>
				</kendo:grid-toolbar>
				<!-- specify the endpoints to call for read, update, create and destroy operations -->
				<kendo:dataSource pageSize="5" serverPaging="true">
					<kendo:dataSource-transport>
						<kendo:dataSource-transport-read url="api/products" />
						<kendo:dataSource-transport-update url="api/products?update" type="POST" />
						<kendo:dataSource-transport-create url="api/products?create" type="POST" />
						<kendo:dataSource-transport-destroy url="api/products?destroy" type="POST" /> 
					</kendo:dataSource-transport>
					<!-- tell the datasource about the structure of the data in the schema -->
					<kendo:dataSource-schema total="Total" data="Data">
						<kendo:dataSource-schema-model id="ProductID">
							<kendo:dataSource-schema-model-fields>
								<kendo:dataSource-schema-model-field name="Supplier" defaultValue="<%= new Supplier() %>"></kendo:dataSource-schema-model-field>
								<kendo:dataSource-schema-model-field name="Category" defaultValue="<%= new Category() %>"></kendo:dataSource-schema-model-field>
								<kendo:dataSource-schema-model-field name="ProductName">
									<kendo:dataSource-schema-model-field-validation required="true"/>
								</kendo:dataSource-schema-model-field>
								<kendo:dataSource-schema-model-field name="UnitsInStock" type="number">
									<kendo:dataSource-schema-model-field-validation min="1" required="true"/>
								</kendo:dataSource-schema-model-field>
								<kendo:dataSource-schema-model-field name="UnitPrice" type="number">
									<kendo:dataSource-schema-model-field-validation min="1" required="true"/>
								</kendo:dataSource-schema-model-field>
								<kendo:dataSource-schema-model-field name="Discontinued" type="boolean" />
							</kendo:dataSource-schema-model-fields>
						</kendo:dataSource-schema-model>
					</kendo:dataSource-schema>
				</kendo:dataSource>
				<!-- grid column definitions -->
				<kendo:grid-columns>
					<kendo:grid-column title="Name" field="ProductName"></kendo:grid-column>
					<kendo:grid-column title="Supplier" field="Supplier" template="#: Supplier.SupplierName #" editor="supplierEditor"></kendo:grid-column>
					<kendo:grid-column title="Category" field="Category" template="#: Category.CategoryName #" editor="categoryEditor"></kendo:grid-column>
					<kendo:grid-column title="# In Stock" field="UnitsInStock"></kendo:grid-column>
					<kendo:grid-column title="Price" field="UnitPrice"></kendo:grid-column>
					<kendo:grid-column field="Discontinued"></kendo:grid-column>
					<kendo:grid-column width="175px" >
						<kendo:grid-column-command>
							<kendo:grid-column-commandItem name="edit"></kendo:grid-column-commandItem>
							<kendo:grid-column-commandItem name="destroy"></kendo:grid-column-commandItem>
						</kendo:grid-column-command>
					</kendo:grid-column>
				</kendo:grid-columns>
			</kendo:grid>
		</div>
	</div>
</div>

<script type="text/javascript">

	// this function creates a Kendo UI DropDown List which will be attached to the row in the grid
	// where it is required
	function supplierEditor(container, options) {
		// create an input field with jQuery and configure it's values with declarative intialization
		// see http://docs.kendoui.com/howto/declarative_initialization for more information
		$("<input data-text-field='SupplierName' data-value-field='SupplierID' data-bind='value: " + options.field + "' />")
		// append it to the container (grid row currently in edit mode)
		.appendTo(container)
		// create the Kendo UI DropDown List
		.kendoDropDownList({
			dataSource: {
				// define the endpoint that will provide the data for this control
				transport: {
					read: "api/suppliers"
				}
			}
		});
	}

	// this function creates a Kendo UI DropDown List which will be attached to the row in the grid
	// where it is required
	function categoryEditor(container, options) {
		// create an input field with jQuery and configure it's values with declarative intialization
		// see http://docs.kendoui.com/howto/declarative_initialization for more information
		$("<input data-text-field='CategoryName' data-value-field='CategoryID' data-bind='value: " + options.field + "' />")
		// append it to the container (grid row currently in edit mode)
		.appendTo(container)
		// create the Kendo UI DropDown List
		.kendoDropDownList({
			dataSource: {
				// define the endpoint that will provide the data for this control
				transport: {
					read: "api/categories"
				}
			}
		});
	}
	
</script>

<shared:footer></shared:footer>