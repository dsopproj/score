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
    public class Message
    {	/** The action. */
        public int action { get; set; }

        /** The content. */
        public String content { get; set; }

        /** The sender. */
        public String sender { get; set; }

        /** The tag. */
        public String tag { get; set; }

        /** The time. */
        public long time { get; set; }


    }

    public static class Action
    {
        /** The Constant Error. */
        public const int Error = -1;

        /** The Constant OK. */
        public const int OK = 0;
    }
}
