using Newtonsoft.Json;
using score.client.Models;
using score.client.Utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace score.client.Modules.Advertis
{
    public partial class Advertisement : UserControl
    {
        private WebClient client;
        private Message msg;
        private string filepath;

        public Advertisement()
        {
            InitializeComponent();
            Initialize();
        }

        private void Initialize()
        {
            client = new WebClient();
            client.DownloadStringCompleted += client_DownloadStringCompleted;
        }


        private void btnSelectImage_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog of = new OpenFileDialog();
            of.Filter = "*.jpg *.png|*.jpg;*.png";
            if (of.ShowDialog().Value)
            {
                try
                {
                    var tmp = new BitmapImage();
                    var fs = of.File.OpenRead();
                    tmp.SetSource(fs);
                    imgAdv.Source = tmp;
                    fs.Close();
                    filepath = of.File.Name;
                }
                catch (Exception ex)
                {
                    MessageBox.Show("访问文件时出错:" + ex.Message);
                }
            }
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
            try
            {
                client.DownloadStringAsync(getReqUrl());
            }
            catch (Exception ex) { }
        }

        private Uri getReqUrl()
        {
            String url = ProjectApiHelper.ADVERTISE_PUBLISH_API + "?username=wolf&title=imagetitle&imagePath=" + filepath;
            return new Uri(url);
        }
    }
}
