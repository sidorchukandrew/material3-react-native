import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface ShowProps {
  id: number;
  gravity?: string;
}

interface Item {
  title?: string;
  groupId?: number;
  icon?: string;
  isCheckable?: boolean;
  isChecked?: boolean;
}

export interface Spec extends TurboModule {
  show: (
    props: ShowProps,
    items: Array<Item>,
    onSelect: (id: number) => void
  ) => void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RTNMenu');
