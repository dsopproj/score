using Newtonsoft.Json;
using score.client.Models;
using score.client.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace score.client.Modules.Cash
{
    public partial class OrderList : UserControl
    {
        private WebClient client;

        public OrderList()
        {
            InitializeComponent();
            initialize();
        }
        private void initialize()
        {
            client = new WebClient();
            client.DownloadStringCompleted += client_DownloadStringCompleted;
            this.Loaded += OrderList_Loaded;
        }

        void OrderList_Loaded(object sender, RoutedEventArgs e)
        {
            btnSearch_Click(null, null);
        }

        void client_DownloadStringCompleted(object sender, DownloadStringCompletedEventArgs e)
        {
            if (e.Error == null)
            {
                try
                {
                    List<Order> list = JsonConvert.DeserializeObject<List<Order>>(e.Result);
                    dgMain.ItemsSource = list;
                }
                catch (Exception ex)
                {
                    MessageBox.Show("parse json error:" + ex.Message);
                }
            }
            else
            {
                MessageBox.Show("url request failure!" + e.Error);
            }
        }

        private void btnSearch_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                client.DownloadStringAsync(getReqUrl());
            }
            catch (Exception ex) { }
        }

        private Uri getReqUrl()
        {
            String url = ProjectApiHelper.OrderList + "?username=wolf";
            return new Uri(url);
        }
    }
}
