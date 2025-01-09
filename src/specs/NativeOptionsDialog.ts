import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface ShowProps {
  title: string;
  negativeButtonText?: string;
  positiveButtonText?: string;
  neutralButtonText?: string;
  cancelable?: boolean;
  headerAlignment?: string;
  pickerType?: string;
  options: string[];
  selected?: number[];
  icon?: string;
}

export interface Spec extends TurboModule {
  show: (
    props: ShowProps,
    onPositivePress?: () => void,
    onNegativePress?: () => void,
    onNeutralPress?: () => void,
    onCancel?: () => void
  ) => void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RTNOptionsDialog');
