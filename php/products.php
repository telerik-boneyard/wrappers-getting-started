<?php
require_once 'lib/Kendo/Autoload.php';
include 'header.php';

// --------- Kendo UI DataSource Transport -------

$create = new \Kendo\Data\DataSourceTransportCreate();
$create->url('/api/products/create.php')
  ->contentType('application/json')
  ->type('POST');

$read = new \Kendo\Data\DataSourceTransportRead();
$read->url('/api/products/read.php')
  ->contentType('application/json')
  ->type('POST');

$update = new \Kendo\Data\DataSourceTransportUpdate();
$update->url('/api/products/update.php')
  ->contentType('application/json')
  ->type('PUT');

$destroy = new \Kendo\Data\DataSourceTransportDestroy();
$destroy->url('/api/products/destroy.php')
  ->contentType('application/json')
  ->type('POST');

$transport = new \Kendo\Data\DataSourceTransport();
$transport
  ->create($create)
  ->read($read)
  ->update($update)
  ->destroy($destroy)
  ->parameterMap('function(data) { return kendo.stringify(data); }');

// --------- Kendo UI DataSource Schema -------

$model = new \Kendo\Data\DataSourceSchemaModel();

$productNameField = new \Kendo\Data\DataSourceSchemaModelField('ProductName');
$productNameField->type('string');

$unitPriceField = new \Kendo\Data\DataSourceSchemaModelField('UnitPrice');
$unitPriceField->type('number');

$unitsInStockField = new \Kendo\Data\DataSourceSchemaModelField('UnitsInStock');
$unitsInStockField->type('number');

$supplierField = new \Kendo\Data\DataSourceSchemaModelField("Supplier");
$supplierField->defaultValue(new stdClass);

$categoryField = new \Kendo\Data\DataSourceSchemaModelField("Category");
$categoryField->defaultValue(new stdClass);

$model->id("ProductID")
  ->addField($productNameField)
  ->addField($unitPriceField)
  ->addField($unitsInStockField)
  ->addField($supplierField)
  ->addField($categoryField);

$schema = new \Kendo\Data\DataSourceSchema();
$schema->data('data')
  ->model($model)
  ->total('total');

// --------- Kendo UI DataSource -------

$dataSource = new \Kendo\Data\DataSource();

$dataSource->transport($transport)
  ->pageSize(5)
  ->schema($schema)
  ->serverPaging(true);

// --------- Kendo UI Grid -------

$grid = new \Kendo\UI\Grid('grid');

$productName = new \Kendo\UI\GridColumn();
$productName->field('ProductName')
  ->title('Product Name');

$supplier = new \Kendo\UI\GridColumn();
$supplier->field('Supplier')
  ->title("Supplier")
  ->editor('supplierEditor')
  ->template("#: Supplier.SupplierName #");

$category = new \Kendo\UI\GridColumn();
$category->field('Category')
  ->title("Category")
  ->editor('categoryEditor')
  ->template("#: Category.CategoryName #");

$unitPrice = new \Kendo\UI\GridColumn();
$unitPrice->field('UnitPrice')
  ->format('{0:c}')
  ->title('Price');

$unitsInStock = new \Kendo\UI\GridColumn();
$unitsInStock->field('UnitsInStock')
  ->title('Stock');

$edit = new \Kendo\UI\GridColumnCommandItem('edit');
$edit->name('edit');

$destroy = new \Kendo\UI\GridColumnCommandItem('destroy');
$destroy->name('destroy');

$commandColumn = new \Kendo\UI\GridColumn();
$commandColumn ->addCommandItem($edit)
  ->addCommandItem($destroy);

$create = new \Kendo\UI\GridToolbarItem('create');

$grid
  ->addToolbarItem($create)
  ->addColumn($productName)
  ->addColumn($supplier)
  ->addColumn($category)
  ->addColumn($unitsInStock)
  ->addColumn($unitPrice)
  ->addColumn($commandColumn)
  ->dataSource($dataSource)
  ->pageable(true)
  ->editable("popup");
?>

<h2>Products</h2>
<p>
A complete CRUD (Create, Read, Update, Delete) demonstration of
the Kendo UI Grid, with custom column templates and pop up editor.
</p>
<?= $grid->render(); ?>

<script>

  function categoryEditor(container, options) {
    $("<input data-text-field='CategoryName' data-value-field='CategoryID' data-bind='value:" + options.field + "' />")
    .appendTo(container)
    .kendoDropDownList({
      index: -1,
      dataSource: {
        transport: {
          read: "api/categories.php"
        }
      }
    });
  };
  
  function supplierEditor(container, options) {
    $("<input data-text-field='SupplierName' data-value-field='SupplierID' data-bind='value:" + options.field + "' />")
    .appendTo(container)
    .kendoDropDownList({
      index: -1,
      dataSource: {
        transport: {
          read: "api/suppliers.php"
        }
      }
    });
  }

</script>
 
<?php
  include 'footer.php';
?>

