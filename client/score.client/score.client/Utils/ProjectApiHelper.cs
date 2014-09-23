using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace score.client.Utils
{
    public class ProjectApiHelper
    {
        private static string BASE_URL = "http://localhost:8080/score";

        public static string PRODUCT_PUBLISH_API { get { return BASE_URL + "/product/publish"; } }
        public static string PRODUCT_LIST_API { get { return BASE_URL + "/products"; } }
        public static string ADVERTISE_CONFIG_API { get { return BASE_URL + "/advertisement/config"; } }
        public static string ADVERTISE_PUBLISH_API { get { return BASE_URL + "/advertisement/publish"; } }

        public static string UrlBalanceValue { get { return BASE_URL + "/account/user/value"; } }
        public static string UrlScoreValue { get { return BASE_URL + "/account/score/value"; } }
        public static string UrlBalanceChange { get { return BASE_URL + "/account/balance/change"; } }
        public static string UrlScoreChange { get { return BASE_URL + "/account/score/change"; } }

        //public static string UrlScoreChange { get { return BASE_URL + "/account/score/change";} }
         
        public static string FindOrderById  { get { return BASE_URL  + "/ShoppingOrder/findorderbyid";} }
        public static string ConsumeOrder { get { return BASE_URL + "/ShoppingOrder/consume"; } }
        public static string OrderList { get { return BASE_URL + "/ShoppingOrder/findorderbyuser"; } }


        public static string GetBaseUrl()
        {
            return BASE_URL;
        }
        public static void SetBaseUrl(string url)
        {
            BASE_URL = url;
        }
    }
}
