using Newtonsoft.Json;
using score.client.Models;
using score.client.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace score.client.Modules.Cash
{
    public partial class ValidateOrder : UserControl
    {
        private int btnOrder = 0;

        WebClient clientConsume = new WebClient { Encoding = Encoding.UTF8 };
        WebClient clientSearch = new WebClient { Encoding = Encoding.UTF8 };
        public ValidateOrder()
        {
            InitializeComponent();
            this.Loaded += ValidateOrder_Loaded;
        }

        void ValidateOrder_Loaded(object sender, RoutedEventArgs e)
        {
            clientConsume.DownloadStringCompleted += clientConsume_DownloadStringCompleted;
            clientSearch.DownloadStringCompleted += clientSearch_DownloadStringCompleted;
        }

        void clientSearch_DownloadStringCompleted(object sender, DownloadStringCompletedEventArgs e)
        {
            if (e.Error == null)
            {
                var msg = JsonConvert.DeserializeObject<Order>(e.Result);

                if (msg != null)
                {
                    txtHaveTag.Visibility = Visibility.Visible;
                    if (msg.state == "0")
                    {
                        btnConsume.Visibility = Visibility.Visible;
                        txtHaveTag.Text = "未消费";
                    }
                    if (msg.state == "1")
                    {
                        btnConsume.Visibility = Visibility.Collapsed;
                        txtHaveTag.Text = "已消费";
                    }
                    if (msg.state == "2")
                    {
                        btnConsume.Visibility = Visibility.Visible;
                        txtHaveTag.Text = "過期";
                    }
                }
            }
        }

        void clientConsume_DownloadStringCompleted(object sender, DownloadStringCompletedEventArgs e)
        {
            if (e.Error == null)
            {
                txtHaveTag.Visibility = Visibility.Visible;
                txtHaveTag.Text = "消费成功";
                btnConsume.Visibility = Visibility.Collapsed;
            }
            else
            {
                MessageBox.Show("系统错误，请重试！");
            }
        }


        private void btnConsume_Click(object sender, RoutedEventArgs e)
        {
            clientConsume.DownloadStringAsync(new Uri(ProjectApiHelper.ConsumeOrder + "?orderid=" + txtOrderNum.Text.Trim()));
        }

        private void btnSearch_Click(object sender, RoutedEventArgs e)
        {
            txtHaveTag.Visibility = Visibility.Collapsed;
            btnConsume.Visibility = Visibility.Collapsed;
            clientSearch.DownloadStringAsync(new Uri(ProjectApiHelper.FindOrderById + "?orderid=" + txtOrderNum.Text.Trim()));
        }
    }
}
