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

namespace score.client.Modules.Advertis
{
    public partial class AdvertisementConfig : UserControl
    {
        private WebClient client;
        private Message msg;
        public AdvertisementConfig()
        {
            InitializeComponent();
            Initialize();
        }

        private void Initialize()
        {
            client = new WebClient();
            client.DownloadStringCompleted += client_DownloadStringCompleted;
        }

        void client_DownloadStringCompleted(object sender, DownloadStringCompletedEventArgs e)
        {
            parseCompleted(e.Error, e.Result);
        }

        private void parseCompleted(Exception Error, String Result)
        {
            if (Error == null)
            {
                try
                {
                    msg = JsonConvert.DeserializeObject<Message>(Result);
                    if (msg.action == 0)
                        MessageBox.Show("success");
                    else
                        MessageBox.Show("failure");
                }
                catch (Exception ex)
                {
                    MessageBox.Show("parse json error:" + ex.Message);
                }
            }
            else
            {
                MessageBox.Show("url request failure!" + Error);
            }
        }


        private void btnCommit_Click(object sender, RoutedEventArgs e)
        {
            client.DownloadStringAsync(getReqUrl());
        }

        private Uri getReqUrl()
        {
            String url = ProjectApiHelper.ADVERTISE_CONFIG_API + "?username=wolf&interval=" + txtInterval.Text + "&speed=" + txtSpeed.Text;
            return new Uri(url);
        }
    }
}
