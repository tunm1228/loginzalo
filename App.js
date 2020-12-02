/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
  NativeModules,
  TouchableOpacity,
  Platform
} from 'react-native';

const { RNLoginZalo } = NativeModules;

const App: () => React$Node = () => {

  return (
    <View style={{ flex: 1 }}>
      <TouchableOpacity
        onPress={() => {
          RNLoginZalo.login((data) => {
              console.log('data', data)
            })
        }}
        style={{
          height: 40,
          width: '100%',
          marginTop: 30,
          backgroundColor: 'red',
          justifyContent: 'center',
          alignItems: 'center'
        }}>
        <Text style={{ color: '#fff', fontSize: 18 }}>Login Zalo</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({

});

export default App;
