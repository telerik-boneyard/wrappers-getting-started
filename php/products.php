<?php
  include 'header.php';

  $create = new \Kendo\Data\DataSourceTransportRead();
  $create->url('/api/products/create.php')
    ->contentType('application/json')
    ->type('POST');

  $read = new \Kendo\Data\DataSourceTransportRead();
  $read->url('/api/products/read.php')
    ->contentType('application/json')
    ->type('POST');

  $update = new \Kendo\Data\DataSourceTransportRead();
  $update->url('/api/products/update.php')
    ->contentType('application/json')
    ->type('PUT');

  $destroy = new \Kendo\Data\DataSourceTransportRead();
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

  $model = new \Kendo\Data\DataSourceSchemaModel();

  $productNameField = new \Kendo\Data\DataSourceSchemaModelField('ProductName');
  $productNameField->type('string');

  $unitPriceField = new \Kendo\Data\DataSourceSchemaModelField('UnitPrice');
  $unitPriceField->type('number');

  $unitsInStockField = new \Kendo\Data\DataSourceSchemaModelField('UnitsInStock');
  $unitsInStockField->type('number');

  $discontinuedField = new \Kendo\Data\DataSourceSchemaModelField('Discontinued');
  $discontinuedField->type('boolean');

  $supplierNameField = new \Kendo\Data\DataSourceSchemaModelField("SupplierName");
  $supplierNameField->type('string');

  $categoryNameField = new \Kendo\Data\DataSourceSchemaModelField("CategoryName");
  $categoryNameField->type('string');

  $model->id("ProductID")
    ->addField($productNameField)
    ->addField($unitPriceField)
    ->addField($unitsInStockField)
    ->addField($discontinuedField)
    ->addField($supplierNameField)
    ->addField($categoryNameField);

  $schema = new \Kendo\Data\DataSourceSchema();
  $schema->data('data')
    ->model($model)
    ->total('total');

  $dataSource = new \Kendo\Data\DataSource();

  $dataSource->transport($transport)
    ->pageSize(10)
    ->schema($schema)
    ->serverFiltering(true)
    ->serverSorting(true)
    ->serverPaging(true);

  $grid = new \Kendo\UI\Grid('grid');

  $productName = new \Kendo\UI\GridColumn();
  $productName->field('ProductName')
    ->title('Product Name');

  $supplier = new \Kendo\UI\GridColumn();
  $supplier->field('SupplierID')
    ->title("Supplier")
    ->editor('supplierEditor')
    ->template("#: SupplierName #");

  $category = new \Kendo\UI\GridColumn();
  $category->field('CategoryID')
    ->title("Category")
    ->editor('categoryEditor')
    ->template("#: CategoryName #");

  $unitPrice = new \Kendo\UI\GridColumn();
  $unitPrice->field('UnitPrice')
    ->format('{0:c}')
    ->title('Unit Price');

  $unitsInStock = new \Kendo\UI\GridColumn();
  $unitsInStock->field('UnitsInStock')
    ->title('Units In Stock');

  $discontinued = new \Kendo\UI\GridColumn();
  $discontinued->field('Discontinued');

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
    ->addColumn($discontinued)
    ->addColumn($commandColumn)
    ->dataSource($dataSource)
    ->pageable(true)
    ->editable("popup");
?>

<div class="container">
  <div class="row">
    <div class="span12">
      <h2>Products</h2>
      <?= $grid->render(); ?>
    </div>
  </div>
</div>

<script>

  function categoryEditor(container, options) {
    $("<input data-text-field='CategoryName' data-value-field='CategoryID' data-bind='value:" + options.field + "' />")
    .appendTo(container)
    .kendoDropDownList({
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

