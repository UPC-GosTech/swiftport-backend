package com.gostech.swiftportbackend.notifications.application.internal.outboundservices.email;

/**
 * SmtpNotificationSender interface
 * This interface is used to send email notifications via SMTP
 */
public interface SmtpNotificationSender {
    
    /**
     * Send an email notification
     * @param to the recipient email address
     * @param subject the email subject
     * @param body the email body content
     * @return boolean true if the email was sent successfully, false otherwise
     */
    boolean sendEmail(String to, String subject, String body);
    
    /**
     * Send an email notification to multiple recipients
     * @param to array of recipient email addresses
     * @param subject the email subject
     * @param body the email body content
     * @return boolean true if the email was sent successfully to all recipients, false otherwise
     */
    boolean sendEmail(String[] to, String subject, String body);
    
    /**
     * Send an HTML email notification
     * @param to the recipient email address
     * @param subject the email subject
     * @param htmlBody the HTML email body content
     * @return boolean true if the email was sent successfully, false otherwise
     */
    boolean sendHtmlEmail(String to, String subject, String htmlBody);
    
    /**
     * Send an HTML email notification to multiple recipients
     * @param to array of recipient email addresses
     * @param subject the email subject
     * @param htmlBody the HTML email body content
     * @return boolean true if the email was sent successfully to all recipients, false otherwise
     */
    boolean sendHtmlEmail(String[] to, String subject, String htmlBody);
}
