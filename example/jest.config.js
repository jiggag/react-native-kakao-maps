module.exports = {
  preset: 'react-native',
  moduleFileExtensions: [
    'ts',
    'tsx',
    'js',
    'jsx',
  ],
  setupFiles: [],
  globals: {
    window: {}
  },
  transformIgnorePatterns: [
    'node_modules/(?!@react-native|react-native|@react-navigation)'
  ],
  moduleNameMapper: {
    'react-query-native-devtools': '<rootDir>/__mocks__/react-query-native-devtools.ts'
  },
  testRegex: '__tests__/.*\\.test\\.(tsx|ts)$'
};
