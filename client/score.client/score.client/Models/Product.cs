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
    public class Product
    {
        public String id { get; set; }
        public String title { get; set; }
        public String imagePath { get; set; }
        public String content { get; set; }
        public Decimal cash { get; set; }
        public String qrCode { get; set; }

    }
}
