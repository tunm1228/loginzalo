//
//  RNLoginZalo.m
//  LoginZalo
//
//  Created by Nguyen Tu on 12/2/20.
//

#import <Foundation/Foundation.h>
#import "RNLoginZalo.h"
#import <React/RCTLog.h>
#import <ZaloSDK/ZaloSDK.h>

@implementation RNLoginZalo
RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(login: (RCTResponseSenderBlock)successCallback)
{
  
  [[ZaloSDK sharedInstance] authenticateZaloWithAuthenType:ZAZAloSDKAuthenTypeViaZaloAppAndWebView
                                          parentController:self                        //controller hiện form đăng nhập
                                                   handler:^(ZOOauthResponseObject *response) { //callback kết quả đăng nhập
    if([response isSucess]) {
      // đăng nhập thành công
      NSMutableDictionary *userObj = [[NSMutableDictionary alloc] init];
      [userObj setObject: @"SUCCESS" forKey:@"code"];
      [userObj setObject:response.oauthCode.length ? response.oauthCode : @"" forKey:@"oauthCode"];
      [userObj setObject:response.userId.length ? response.userId : @""  forKey:@"userId"];
      [userObj setObject:response.displayName.length ? response.displayName : @"" forKey:@"displayName"];
      successCallback(@[userObj]);
      // có thể dùng oauth code này để verify lại từ server của ứng dụng
    } else if(response.errorCode != kZaloSDKErrorCodeUserCancel) {
      NSMutableDictionary *userObj = [[NSMutableDictionary alloc] init];
      [userObj setObject: @"ERROR" forKey:@"code"];
      successCallback(@[userObj]);
      //lỗi đăng nhập
    }
  }];
}
@end
