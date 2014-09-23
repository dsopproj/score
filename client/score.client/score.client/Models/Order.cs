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

namespace score.client.Models
{
    public class Order
    {
        public String orderid { get; set; }
        public String username { get; set; }
        public String shopingid { get; set; }
        public String state { get; set; }
        public String datetime { get; set; }
    }
}
