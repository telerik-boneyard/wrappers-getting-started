using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Kendo.Mvc.Extensions;
using Kendo.Mvc.UI;

namespace aspnetmvc_episode3.Controllers
{
    public class ProductsController : Controller
    {
        readonly Data.SampleEntities _context = new Data.SampleEntities();

        public ActionResult Index() {
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

    }
}
