using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Kendo.Mvc.Extensions;
using Kendo.Mvc.UI;

namespace aspnetmvc_episode4.Controllers
{
    public class ProductsController : Controller {
        readonly Data.SampleEntities _context = new Data.SampleEntities();

        public ActionResult Index() {

            ViewData["defaultSupplier"] = _context.Suppliers.Select(s => new Models.Supplier { Id = s.SupplierID, Name = s.CompanyName }).First();
            ViewData["defaultCategory"] = _context.Categories.Select(c => new Models.Category { Id = c.CategoryID, Name = c.CategoryName }).First();

            return View();
        }

        // parse the incoming request from Kendo UI into a DataSourceRequest
        public JsonResult Get([DataSourceRequest]DataSourceRequest request) {

            // LINQ query to select products and map to model objects
            var products = _context.Products.Select(p => new Models.Product {
                Id = p.ProductID,
                Name = p.ProductName,
                UnitsInStock = p.UnitsInStock,
                UnitPrice = p.UnitPrice,
                Discontinued = p.Discontinued,
                Supplier = new Models.Supplier {
                    Id = p.Supplier.SupplierID,
                    Name = p.Supplier.CompanyName
                },
                Category = new Models.Category {
                    Id = p.Category.CategoryID,
                    Name = p.Category.CategoryName
                }
            });

            // convert to a DataSourceResponse and send back as JSON
            return this.Json(products.ToDataSourceResult(request));

        }

        [AcceptVerbs(HttpVerbs.Post)]
        public JsonResult Update([DataSourceRequest] DataSourceRequest request, Models.Product product) {
            if (product != null && ModelState.IsValid) {
                var productToUpdate = _context.Products.Where(p => p.ProductID == product.Id).FirstOrDefault();
                productToUpdate.ProductName = product.Name;
                productToUpdate.SupplierID = product.Supplier.Id;
                productToUpdate.CategoryID = product.Category.Id;
                productToUpdate.UnitsInStock = product.UnitsInStock;
                productToUpdate.UnitPrice = product.UnitPrice;
                productToUpdate.Discontinued = product.Discontinued;
            }

            _context.SaveChanges();

            return Json(ModelState.IsValid ? true : ModelState.ToDataSourceResult());
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public JsonResult Create([DataSourceRequest] DataSourceRequest request, Models.Product product) {

            if (product != null && ModelState.IsValid) {
                var productToCreate = new Data.Product {
                    ProductID = product.Id,
                    ProductName = product.Name,
                    SupplierID = product.Supplier.Id,
                    CategoryID = product.Category.Id,
                    UnitsInStock = product.UnitsInStock,
                    UnitPrice = product.UnitPrice,
                    Discontinued = product.Discontinued
                };

                _context.Products.Add(productToCreate);
                _context.SaveChanges();

            }

            return Json(ModelState.IsValid ? true : ModelState.ToDataSourceResult());

        }

        [AcceptVerbs(HttpVerbs.Post)]
        public JsonResult Delete([DataSourceRequest] DataSourceRequest request, Models.Product product) {

            if (product != null && ModelState.IsValid) {
                var productToDelete = _context.Products.Where(p => p.ProductID == product.Id).FirstOrDefault();

                _context.Products.Remove(productToDelete);
                _context.SaveChanges();
            }

            return Json(ModelState.IsValid ? true : ModelState.ToDataSourceResult());
        }
    }
}
